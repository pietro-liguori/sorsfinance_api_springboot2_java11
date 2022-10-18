package com.vili.sorsfinance.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.api.domain.CreditPayment;
import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.api.domain.dto.PaymentDTO;
import com.vili.sorsfinance.api.domain.dto.TransactionDTO;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.domain.enums.TransactionDirection;
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(Transaction.class)
public class TransactionService implements IService {

	@Autowired
	AccountService accService;
	@Autowired
	CardService cardService;
	@Autowired
	TransactionItemService itemService;
	@Autowired
	PaymentService payService;
	@Autowired
	CreditPaymentService credPayService;
	@Autowired
	CreditCardStatementService statementService;

	@Override
	public Transaction save(IDataTransferObject dto) {
		TransactionDTO transDTO = (TransactionDTO) dto;
		Transaction trans = transDTO.toEntity();
		trans = (Transaction) save(trans);
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			trans = saveItems(trans);
			trans = savePayments(trans);
	
			switch (TransactionType.toEnum(trans.getType())) {
			case BANK_SLIP_PAYMENT:
				processBankSlipPayment(transDTO);
				break;
			case CHECK_PAYMENT:
				processCheckPayment(transDTO);
				break;
			case CREDITCARD_STATEMENT_PAYMENT:
				processCreditCardStatementPayment(transDTO);
				break;
			case TRANSFER_BETWEEN_HOLDERS:
				processTransferBetweenHolders(transDTO);
				break;
			case WITHDRAW:
				processWithdraw(transDTO);
				break;
			default:
				break;
			}
		}
		return trans;
	}

	private void processBankSlipPayment(TransactionDTO dto) {
		/*
		 * TODO UML: create BankSlipPayment ? create BankSlip ?
		 */
	}

	private void processCheckPayment(TransactionDTO dto) {
		/*
		 * TODO UML: create CheckPayment ? create Check ?
		 */
	}

	private void processCreditCardStatementPayment(TransactionDTO dto) {
		for (PaymentDTO payDTO : dto.getPayments()) {
			CreditCardStatement statement = (CreditCardStatement) statementService
					.findById(payDTO.getCreditCardStatementId());
			statement.setPaidValue(payDTO.getValue());
			statement.setPayDate(dto.getDate());
			statement.setPayment(payDTO.toEntity());

			if (statement.getTotal() == statement.getPaidValue())
				statement.setStatus(PaymentStatus.PAID);
			else if (statement.getTotal() > statement.getPaidValue()) {
				statement.setStatus(PaymentStatus.OVER_PAID);
				// TODO create discount installment on next statement
			} else {
				statement.setStatus(PaymentStatus.UNDER_PAID);
				// TODO create installment on next statement
			}
		}
	}

	private void processTransferBetweenHolders(TransactionDTO dto) {
		Transaction trans = dto.toEntity();

		for (PaymentDTO payDTO : dto.getPayments()) {
			Account acc = (Account) accService.findById(payDTO.getTransferAccountId());
			String description = "Transferência via " + PaymentType.toEnum(payDTO.getType()).getLabel();
			Transaction aux = new Transaction(null, acc.getHolder(), trans.getDate(), description,
					trans.getDiscount(), TransactionType.INTERNAL, TransactionDirection.INPUT);
			trans.getCategories().forEach(x -> aux.addCategory(x));
			aux.addPayment(payDTO.toEntity());
			save(aux);
		}
	}

	private void processWithdraw(TransactionDTO dto) {
		Transaction trans = dto.toEntity();

		for (PaymentDTO payDTO : dto.getPayments()) {
			Account acc = (Account) accService.findById(payDTO.getTransferAccountId());
			String description = "Saque em dinheiro";
			Transaction aux = new Transaction(null, acc.getHolder(), trans.getDate(), description,
					trans.getDiscount(), TransactionType.INTERNAL, TransactionDirection.INPUT);
			trans.getCategories().forEach(x -> aux.addCategory(x));
			aux.addPayment(payDTO.toEntity());
			save(aux);
		}
	}

	private Transaction saveItems(Transaction transaction) {
		for (TransactionItem item : transaction.getItems()) {
			item.setTransaction(transaction);
			item = (TransactionItem) itemService.save(item);
		}

		return transaction;
	}

	private Transaction savePayments(Transaction transaction) {
		TransactionDirection direction = TransactionDirection.toEnum(transaction.getDirection());

		for (Payment pay : transaction.getPayments()) {
			pay.setTransaction(transaction);
			PaymentType payType = PaymentType.toEnum(pay.getType());

			switch (payType) {
			case BANK_SLIP:
				
				break;
			case CHECK:
				
				break;
			case CREDIT:
				if (direction.equals(TransactionDirection.INPUT))
					pay.setValue(-pay.getValue());

				credPayService.save((CreditPayment) pay);
				break;
			default:
				if (payType.equals(PaymentType.VOUCHER)) { 
					Voucher card = (Voucher) cardService.findById(pay.getCard().getId());
					card = handleVoucherBalance(card, pay.getValue(), direction);
					cardService.save(card);
				} else {
					Account acc = (Account) accService.findById(pay.getAccount().getId());
					acc = handleAccountBalance(acc, pay.getValue(), direction);
					accService.save(acc);
				}
				
				payService.save(pay);
				break;
			}
		}

		return transaction;
	}
	
	private Account handleAccountBalance(Account account, Double value, TransactionDirection direction) {
		if (direction.equals(TransactionDirection.OUTPUT))
			value = -value;

		if (Account.BANK_ACCOUNT_TYPES.contains(AccountType.toEnum(account.getType()))) {
			((BankAccount) account).setBalance(((BankAccount) account).getBalance() - value);
		}
		
		if (Account.WALLET_TYPES.contains(AccountType.toEnum(account.getType()))) {
			((Wallet) account).setBalance(((Wallet) account).getBalance() - value);
		}
		
		return account;
	}
	
	private Voucher handleVoucherBalance(Voucher card, Double value, TransactionDirection direction) {
		if (direction.equals(TransactionDirection.OUTPUT))
			value = -value;

		card.setBalance(card.getBalance() - value);
		return card;
	}
}
