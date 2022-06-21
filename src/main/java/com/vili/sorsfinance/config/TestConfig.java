package com.vili.sorsfinance.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vili.sorsfinance.api.entities.Address;
import com.vili.sorsfinance.api.entities.BankAccount;
import com.vili.sorsfinance.api.entities.Branch;
import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.Category;
import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.entities.Country;
import com.vili.sorsfinance.api.entities.CreditCard;
import com.vili.sorsfinance.api.entities.CreditCardStatement;
import com.vili.sorsfinance.api.entities.CreditInstallment;
import com.vili.sorsfinance.api.entities.CreditPayment;
import com.vili.sorsfinance.api.entities.Email;
import com.vili.sorsfinance.api.entities.Payment;
import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.Phone;
import com.vili.sorsfinance.api.entities.Product;
import com.vili.sorsfinance.api.entities.ServiceProvision;
import com.vili.sorsfinance.api.entities.State;
import com.vili.sorsfinance.api.entities.TicketAccount;
import com.vili.sorsfinance.api.entities.Transaction;
import com.vili.sorsfinance.api.entities.TransactionItem;
import com.vili.sorsfinance.api.entities.Wallet;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.enums.ContactType;
import com.vili.sorsfinance.api.entities.enums.PaymentStatus;
import com.vili.sorsfinance.api.entities.enums.PaymentType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.entities.enums.PersonType;
import com.vili.sorsfinance.api.entities.enums.PhoneType;
import com.vili.sorsfinance.api.entities.enums.TransactionType;
import com.vili.sorsfinance.api.repositories.BusEntityRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private BusEntityRepository repository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Lazer");
		Category cat2 = new Category(null, "Viagem");
		Category cat3 = new Category(null, "Hospedagem");
		Category cat4 = new Category(null, "Educação");
		Category cat5 = new Category(null, "Curso");
		Category cat6 = new Category(null, "Eletrônicos");
		Category cat7 = new Category(null, "Costura");
		Category cat8 = new Category(null, "Massagem");
		Category cat9 = new Category(null, "Alimentos");
		Category cat10 = new Category(null, "Queijos");
		Category cat11 = new Category(null, "Eletrodomésticos");
		Category cat12 = new Category(null, "Transporte");
		Category cat13 = new Category(null, "Açougue");

		repository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13));
		
		Country cnt1 = new Country(null, "Brasil", "BRA");
		
		State st1 = new State(null, "São Paulo", "SP", cnt1);
		State st2 = new State(null, "Rio de Janeiro", "RJ", cnt1);
		
		City cty1 = new City(null, "São Paulo", st1);
		City cty2 = new City(null, "Campinas", st1);
		City cty3 = new City(null, "Vinhedo", st1);
		City cty4 = new City(null, "Rio de Janeiro", st2);
		City cty5 = new City(null, "Petrópolis", st2);
		City cty6 = new City(null, "Paraty", st2);
		
		
		repository.saveAll(Arrays.asList(cnt1));
		repository.saveAll(Arrays.asList(st1, st2));
		repository.saveAll(Arrays.asList(cty1, cty2, cty3, cty4, cty5, cty6));
		
		Branch bch1 = new Branch(null, "Pessoa Física");
		Branch bch2 = new Branch(null, "Varejo de móveis e eletrodomésticos");
		Branch bch3 = new Branch(null, "Costureira");
		Branch bch4 = new Branch(null, "Banco");
		
		repository.saveAll(Arrays.asList(bch1, bch2, bch3, bch4));
				
		Person p1 = new Person(null, "Pietro Magalhães Liguori", "36872371846", PersonType.NATURAL_PERSON, PersonProfile.HOLDER);
		Person p2 = new Person(null, "Santander", null, PersonType.LEGAL_PERSON, PersonProfile.BANK);
		Person p3 = new Person(null, "Casas Bahia", null, PersonType.LEGAL_PERSON, PersonProfile.STANDARD);
		Person p4 = new Person(null, "Casa da costura", null, PersonType.LEGAL_PERSON, PersonProfile.STANDARD);
		Person p5 = new Person(null, "Sodexo", null, PersonType.LEGAL_PERSON, PersonProfile.STANDARD);

		p1.setBranch(bch1);
		p2.setBranch(bch4);
		p3.setBranch(bch2);
		p4.setBranch(bch3);
		p5.setBranch(bch4);

		Wallet acc1 = new Wallet(null, "Carteira Pietro", p1, 0.0, 0.0, AccountType.WALLET, AccountStatus.ACTIVE);
		BankAccount acc2 = new BankAccount(null, "Santander Pietro", p1, "01013389-4", "0216", p2, 0.0, 1800.0, 0.08, PeriodUnit.MONTH, 10, PeriodUnit.DAY, 2800.0, AccountType.CHECKING_ACCOUNT, AccountStatus.ACTIVE);
		TicketAccount acc3 = new TicketAccount(null, "Sodexo Pietro", p1, p5, 0.0, AccountType.TICKET_ACCOUNT, AccountStatus.ACTIVE);

		Card cd1 = new Card(null, "Santander Universitário", acc2, "1536", null, CardType.MULTIPLE, CardStatus.UNBLOCKED);
		CreditCard cd2 = new CreditCard(null, "Crédito Santander", acc2, "0714", null, CardType.CREDIT, CardStatus.UNBLOCKED, 13, 10, PeriodUnit.DAY, 0.14, PeriodUnit.MONTH);
		Card cd3 = new Card(null, "VR Sodexo", acc3, "4879", null, CardType.MEAL_TICKET, CardStatus.UNBLOCKED);

		acc2.addCard(cd1);
		acc2.addCard(cd2);
		acc3.addCard(cd3);
		
		p1.addAccount(acc1);
		p1.addAccount(acc2);
		p1.addAccount(acc3);

		repository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		repository.saveAll(Arrays.asList(acc1, acc2, acc3));
		repository.saveAll(Arrays.asList(cd1, cd2, cd3));

		Contact ctc1 = new Contact(null, p1, ContactType.PHONE);

		repository.saveAll(Arrays.asList(ctc1));

		Address adr1 = new Address(null, "Rua João Ferragut", "235", "Torre 7, apto 21", "Pinheirinho", "13289-476", cty3, true);
		Email em1 = new Email(null, "pietro_liguori@hotmail.com", true);
		Phone ph1 = new Phone(null, "11996758494", PhoneType.MOBILE, true);
		
		adr1.addContact(ctc1);
		em1.setContact(ctc1);
		ph1.setContact(ctc1);
		
		repository.saveAll(Arrays.asList(adr1));
		repository.saveAll(Arrays.asList(em1));
		repository.saveAll(Arrays.asList(ph1));

		Product a1 = new Product(null, "Televisão", AssetType.PRODUCT, null, "Samsung");
		ServiceProvision a2 = new ServiceProvision(null, "Conserto de calça jeans", AssetType.SERVICE_PROVISION, null);
		Product a3 = new Product(null, "Liquidificador", AssetType.PRODUCT, null, "Philco");
		Product a4 = new Product(null, "Queijo Brie", AssetType.PRODUCT, null, "Tirolez");
		ServiceProvision a5 = new ServiceProvision(null, "Massagem modeladora", AssetType.SERVICE_PROVISION, null);
		Product a6 = new Product(null, "Queijo Parmesão", AssetType.PRODUCT, null, "Tirolez");
		Product a7 = new Product(null, "Peito de Frango", AssetType.PRODUCT, null, "Covabra");

		a1.addCategories(cat6);
		a2.addCategories(cat7);
		a3.addCategories(cat11);
		a4.addCategories(cat9, cat10);
		a5.addCategories(cat8);
		a6.addCategories(cat9, cat10);
		a7.addCategories(cat9, cat13);

		repository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt1 = sdf.parse("12/02/2020");
		Date dt2 = sdf.parse("12/02/2022");

		Transaction t1 = new Transaction(null, p3, new java.sql.Date(dt1.toInstant().toEpochMilli()), "Compra Casas Bahia", null, TransactionType.DEFAULT);
		Transaction t2 = new Transaction(null, p4, new java.sql.Date(dt2.toInstant().toEpochMilli()), "Costureira", null, TransactionType.DEFAULT);
		
		t1.addCategories(cat6);
		t2.addCategories(cat7);
		repository.saveAll(Arrays.asList(t1, t2));

		TransactionItem ti1 = new TransactionItem(null, t1, a1, 800.0, 1, 0.0);
		TransactionItem ti2 = new TransactionItem(null, t1, a3, 200.0, 2, 0.0);
		TransactionItem ti3 = new TransactionItem(null, t2, a2, 30.0, 3, 0.0);

		t1.addItems(ti1, ti2);
		t2.addItem(ti3);

		repository.saveAll(Arrays.asList(ti1, ti2, ti3));
		
		CreditPayment pay1 = new CreditPayment(null, "Pagamento no crédito 3x", PaymentType.CREDIT, 1200.0, PaymentStatus.PAID, acc2, p1, t1, cd1, 3);
		Payment pay2 = new Payment(null, "Pagamento em dinheiro", PaymentType.CASH, 50.0, PaymentStatus.PAID, acc1, p1, t2, null);
		Payment pay3 = new Payment(null, "Pagamento no débito", PaymentType.DEBIT, 40.0, PaymentStatus.PAID, acc2, p1, t2, cd1);
		
		List<Double> iv1 = pay1.getInstallmentValues(null);
		
		t1.addPayment(pay1);
		t2.addPayment(pay2);
		t2.addPayment(pay3);

		repository.saveAll(Arrays.asList(pay1, pay2, pay3));
		
		for (int i = 1; i <= pay1.getInstallments(); i++) {
			CreditCardStatement st = new CreditCardStatement(null, cd2, "Fatura " + i, new java.sql.Date(1524651548), new java.sql.Date(1524651548), PaymentStatus.NOT_PAID);
			CreditInstallment pi = new CreditInstallment(null, pay1, st, iv1.get(i - 1), i, PaymentStatus.NOT_PAID);
			repository.save(st);
			repository.save(pi);
		}
	}
}
