package com.vili.sorsfinance.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.BankAccount;
import com.vili.sorsfinance.api.entities.TicketAccount;
import com.vili.sorsfinance.api.entities.Wallet;
import com.vili.sorsfinance.api.entities.dto.AccountDTO;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.filters.AccountFilter;
import com.vili.sorsfinance.api.framework.BusEntity;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.services.BankAccountService;
import com.vili.sorsfinance.api.services.TicketAccountService;
import com.vili.sorsfinance.api.services.WalletService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource extends DefaultResource<Account, AccountDTO> {

	@Autowired
	BankAccountService bankAccountService;
	@Autowired
	TicketAccountService ticketService;
	@Autowired
	WalletService walletService;
	@Autowired
	AccountRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Account>> U getFilter() {
		return (U) new AccountFilter(Request.from(getRequest()), repo);
	}

	@Override
	@PostMapping
	public ResponseEntity<Account> insert(@Valid @RequestBody AccountDTO dto) {
		AccountType type = AccountType.toEnum(dto.getType());
		Account obj = null;

		if (Account.BANK_ACCOUNT_TYPES.contains(type))
			obj = bankAccountService.save((BankAccount) BusEntity.fromDTO(dto));

		if (Account.TICKET_ACCOUNT_TYPES.contains(type))
			obj = ticketService.save((TicketAccount) BusEntity.fromDTO(dto));

		if (Account.WALLET_TYPES.contains(type))
			obj = walletService.save((Wallet) BusEntity.fromDTO(dto));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}