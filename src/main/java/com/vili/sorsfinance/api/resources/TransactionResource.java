package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.dto.TransactionDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(Transaction.class)
@RequestMapping(value = "/transactions")
public class TransactionResource implements IResource<TransactionDTO>{

//	@Autowired
//	TransactionItemService itemService;
//	@Autowired
//	PaymentService payService;
//	@Autowired
//	CreditPaymentService credPayService;
//
//	@Override
//	@PostMapping
//	public ResponseEntity<Transaction> insert(@Valid @RequestBody TransactionDTO dto) {
//		TransactionType type = TransactionType.toEnum(dto.getType());
//		Transaction obj = Transaction.fromDTO(dto);
//		
//		obj = service.save(obj);
//		
//		for (TransactionItemDTO itemDto : dto.getItems()) {
//			TransactionItem item = TransactionItem.fromDTO(itemDto);
//			item.setTransaction(obj);
//			item = itemService.save(item);
//			obj.addItem(item);
//		}
//		
//		for (PaymentDTO payDto : dto.getPayments()) {
//			PaymentType payType = PaymentType.toEnum(payDto.getType());
//			payDto.setTransactionId(obj.getId());
//
//			if (payType.equals(PaymentType.CREDIT)) {
//				CreditPayment pay = CreditPayment.fromDTO(payDto);
//				pay = credPayService.save(pay);
//				obj.addPayment(pay);
//			} else {
//				Payment pay = Payment.fromDTO(payDto);
//				pay = payService.save(pay);
//				obj.addPayment(pay);
//			}
//		}
//
//		switch (type) {
//		case DEFAULT:
//			// TODO
//			break;
//		case BETWEEN_HOLDERS:
//			// TODO
//			break;
//		case CREDITCARD_STATEMENT_PAYMENT:
//			// TODO
//			break;
//		case WITHDRAW:
//			// TODO
//			break;
//		default:
//			// TODO
//			break;
//		}
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).body(obj);
//	}
}
