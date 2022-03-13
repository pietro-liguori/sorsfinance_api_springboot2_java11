package com.vili.sorsfinance.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vili.sorsfinance.entities.Account;
import com.vili.sorsfinance.entities.Address;
import com.vili.sorsfinance.entities.Asset;
import com.vili.sorsfinance.entities.BankAccount;
import com.vili.sorsfinance.entities.Branch;
import com.vili.sorsfinance.entities.Card;
import com.vili.sorsfinance.entities.Category;
import com.vili.sorsfinance.entities.City;
import com.vili.sorsfinance.entities.Contact;
import com.vili.sorsfinance.entities.Country;
import com.vili.sorsfinance.entities.CreditCard;
import com.vili.sorsfinance.entities.CreditCardStatement;
import com.vili.sorsfinance.entities.CreditPayment;
import com.vili.sorsfinance.entities.Email;
import com.vili.sorsfinance.entities.Payment;
import com.vili.sorsfinance.entities.PaymentInstallment;
import com.vili.sorsfinance.entities.Person;
import com.vili.sorsfinance.entities.Phone;
import com.vili.sorsfinance.entities.Product;
import com.vili.sorsfinance.entities.ServiceProvision;
import com.vili.sorsfinance.entities.State;
import com.vili.sorsfinance.entities.Transaction;
import com.vili.sorsfinance.entities.TransactionItem;
import com.vili.sorsfinance.entities.enums.AccountStatus;
import com.vili.sorsfinance.entities.enums.AccountType;
import com.vili.sorsfinance.entities.enums.AssetType;
import com.vili.sorsfinance.entities.enums.CardStatus;
import com.vili.sorsfinance.entities.enums.CardType;
import com.vili.sorsfinance.entities.enums.CategoryType;
import com.vili.sorsfinance.entities.enums.ContactType;
import com.vili.sorsfinance.entities.enums.PaymentStatus;
import com.vili.sorsfinance.entities.enums.PaymentType;
import com.vili.sorsfinance.entities.enums.PeriodUnit;
import com.vili.sorsfinance.entities.enums.PersonProfile;
import com.vili.sorsfinance.entities.enums.PersonType;
import com.vili.sorsfinance.entities.enums.PhoneType;
import com.vili.sorsfinance.entities.enums.TransactionType;
import com.vili.sorsfinance.repositories.AccountRepository;
import com.vili.sorsfinance.repositories.AddressRepository;
import com.vili.sorsfinance.repositories.AssetRepository;
import com.vili.sorsfinance.repositories.BranchRepository;
import com.vili.sorsfinance.repositories.CardRepository;
import com.vili.sorsfinance.repositories.CategoryRepository;
import com.vili.sorsfinance.repositories.CityRepository;
import com.vili.sorsfinance.repositories.ContactRepository;
import com.vili.sorsfinance.repositories.CountryRepository;
import com.vili.sorsfinance.repositories.CreditCardStatementRepository;
import com.vili.sorsfinance.repositories.EmailRepository;
import com.vili.sorsfinance.repositories.PaymentInstallmentRepository;
import com.vili.sorsfinance.repositories.PaymentRepository;
import com.vili.sorsfinance.repositories.PersonRepository;
import com.vili.sorsfinance.repositories.PhoneRepository;
import com.vili.sorsfinance.repositories.StateRepository;
import com.vili.sorsfinance.repositories.TransactionItemRepository;
import com.vili.sorsfinance.repositories.TransactionRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private AssetRepository assetRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionItemRepository transactionItemRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private CreditCardStatementRepository creditCardStatementRepository;
	@Autowired
	private PaymentInstallmentRepository paymentInstallmentRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Lazer", 1, CategoryType.TRANSACTION);
		Category cat2 = new Category(null, "Viagem", 2, CategoryType.TRANSACTION);
		Category cat3 = new Category(null, "Hospedagem", 3, CategoryType.TRANSACTION);
		Category cat4 = new Category(null, "Educação", 1, CategoryType.TRANSACTION);
		Category cat5 = new Category(null, "Curso", 2, CategoryType.TRANSACTION);
		Category cat6 = new Category(null, "Eletrônicos", 1, CategoryType.ASSET);
		Category cat7 = new Category(null, "Costura", 1, CategoryType.ASSET);
		Category cat8 = new Category(null, "Massagem", 1, CategoryType.ASSET);
		Category cat9 = new Category(null, "Alimentos", 1, CategoryType.ASSET);
		Category cat10 = new Category(null, "Queijos", 2, CategoryType.ASSET);
		Category cat11 = new Category(null, "Eletrodomésticos", 1, CategoryType.ASSET);
		Category cat12 = new Category(null, "Transporte", 1, CategoryType.TRANSACTION);


		cat1.addChild(cat2);
		cat1.addChild(cat12);
		cat2.addChild(cat3);
		cat4.addChild(cat5);
		cat9.addChild(cat10);
		
		cat12.setParent(cat1);
		cat3.setParent(cat2);
		cat5.setParent(cat4);
		cat10.setParent(cat9);
		cat2.setParent(cat1);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		
		Country cnt1 = new Country(null, "Brasil", "BRA");
		
		State st1 = new State(null, "São Paulo", "SP", cnt1);
		State st2 = new State(null, "Rio de Janeiro", "RJ", cnt1);
		
		City cty1 = new City(null, "São Paulo", st1);
		City cty2 = new City(null, "Campinas", st1);
		City cty3 = new City(null, "Vinhedo", st1);
		City cty4 = new City(null, "Rio de Janeiro", st2);
		City cty5 = new City(null, "Petrópolis", st2);
		City cty6 = new City(null, "Paraty", st2);
		
		countryRepository.saveAll(Arrays.asList(cnt1));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(cty1, cty2, cty3, cty4, cty5, cty6));
		
		Contact ctc1 = new Contact(null, ContactType.PHONE);
		Address adr1 = new Address(null, "Rua João Ferragut", "235", "Torre 7, apto 21", "Pinheirinho", "13289-476", cty3, true);
		Email em1 = new Email(null, "pietro_liguori@hotmail.com", true);
		Phone ph1 = new Phone(null, "11996758494", PhoneType.MOBILE, true);
		
		ctc1.addPhone(ph1);
		ctc1.addAddress(adr1);
		ctc1.addEmail(em1);
		
		addressRepository.saveAll(Arrays.asList(adr1));
		emailRepository.saveAll(Arrays.asList(em1));
		phoneRepository.saveAll(Arrays.asList(ph1));
		contactRepository.saveAll(Arrays.asList(ctc1));

		Branch bch1 = new Branch(null, "Pessoa Física");
		Branch bch2 = new Branch(null, "Varejo de móveis e eletrodomésticos");
		Branch bch3 = new Branch(null, "Costureira");
		Branch bch4 = new Branch(null, "Banco");
		
		branchRepository.saveAll(Arrays.asList(bch1, bch2, bch3, bch4));
				
		Person p1 = new Person(null, "Pietro Magalhães Liguori", "36872371846", PersonType.NATURAL_PERSON, PersonProfile.HOLDER);
		Person p2 = new Person(null, "Santander", null, PersonType.LEGAL_PERSON, PersonProfile.BANK);
		Person p3 = new Person(null, "Casas Bahia", null, PersonType.LEGAL_PERSON, PersonProfile.STANDARD);
		Person p4 = new Person(null, "Casa da costura", null, PersonType.LEGAL_PERSON, PersonProfile.STANDARD);

		p1.setContact(ctc1);
		
		p1.setBranch(bch1);
		p2.setBranch(bch4);
		p3.setBranch(bch2);
		p4.setBranch(bch3);

		Account acc1 = new Account(null, "Carteira Pietro", p1, 0.0, AccountType.WALLET, AccountStatus.ACTIVE);
		BankAccount acc2 = new BankAccount(null, "Santander Pietro", p1, "01013389-4", "0216", p2, 0.0, 1800.0, 0.08, PeriodUnit.MONTH, 10, PeriodUnit.DAY, 2800.0, AccountType.CHECKING_ACCOUNT, AccountStatus.ACTIVE);

		Card cd1 = new Card(null, "Santander Universitário", acc2, "1536", null, CardType.MULTIPLE, CardStatus.UNBLOCKED);
		CreditCard cd2 = new CreditCard(null, "Crédito Santander", acc2, "0714", null, CardType.CREDIT, CardStatus.UNBLOCKED, 13, 10, PeriodUnit.DAY, 0.14, PeriodUnit.MONTH);
		
		acc2.addCard(cd1);
		acc2.addCard(cd2);
		
		p1.addAccount(acc1);
		p1.addAccount(acc2);

		personRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		accountRepository.saveAll(Arrays.asList(acc1, acc2));
		cardRepository.saveAll(Arrays.asList(cd1, cd2));
		
		Asset a1 = new Product(null, "Televisão", AssetType.PRODUCT, cat6, null, "Samsung");
		Asset a2 = new ServiceProvision(null, "Conserto de calça jeans", AssetType.SERVICE_PROVISION, cat7, null);
		Asset a3 = new Product(null, "Liquidificador", AssetType.PRODUCT, cat11, null, "Philco");
		Asset a4 = new Product(null, "Queijo Brie", AssetType.PRODUCT, cat8, null, "Tirolez");
		Asset a5 = new ServiceProvision(null, "Massagem modeladora", AssetType.SERVICE_PROVISION, cat9, null);

		assetRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

		Transaction t1 = new Transaction(null, p3, new java.sql.Date(1524651548), "Compra Casas Bahia", 0.0, cat6, TransactionType.DEFAULT);
		Transaction t2 = new Transaction(null, p4, new java.sql.Date(1524651548), "Costureira", 0.0, cat7, TransactionType.DEFAULT);
		
		transactionRepository.saveAll(Arrays.asList(t1, t2));

		TransactionItem ti1 = new TransactionItem(t1, a1, 800.0, 1, 0.0);
		TransactionItem ti2 = new TransactionItem(t1, a3, 200.0, 2, 0.0);
		TransactionItem ti3 = new TransactionItem(t2, a2, 30.0, 3, 0.0);

		t1.addItems(Arrays.asList(ti1, ti2));
		t2.addItem(ti3);

		transactionItemRepository.saveAll(Arrays.asList(ti1, ti2, ti3));
		
		CreditPayment pay1 = new CreditPayment(null, PaymentType.CREDIT, 1200.0, PaymentStatus.PAID, acc2, p1, t1, cd1, 3);
		Payment pay2 = new Payment(null, PaymentType.CASH, 50.0, PaymentStatus.PAID, acc1, p1, t2, null);
		Payment pay3 = new Payment(null, PaymentType.DEBIT, 40.0, PaymentStatus.PAID, acc2, p1, t2, cd1);
		
		List<Double> iv1 = pay1.getInstallmentValues(null);
		
		t1.addPayment(pay1);
		t2.addPayment(pay2);
		t2.addPayment(pay3);

		paymentRepository.saveAll(Arrays.asList(pay1, pay2, pay3));
		
		for (int i = 1; i <= pay1.getInstallments(); i++) {
			CreditCardStatement st = new CreditCardStatement(null, cd2, "Fatura " + i, new java.sql.Date(1524651548), new java.sql.Date(1524651548), PaymentStatus.NOT_PAID);
			PaymentInstallment pi = new PaymentInstallment(pay1, st, iv1.get(i - 1), i, PaymentStatus.NOT_PAID);
			creditCardStatementRepository.save(st);
			paymentInstallmentRepository.save(pi);
		}
		
	}
	
	
}
