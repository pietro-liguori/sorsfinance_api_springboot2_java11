package com.vili.sorsfinance.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vili.sorsfinance.entities.Account;
import com.vili.sorsfinance.entities.Address;
import com.vili.sorsfinance.entities.BankAccount;
import com.vili.sorsfinance.entities.Branch;
import com.vili.sorsfinance.entities.Category;
import com.vili.sorsfinance.entities.City;
import com.vili.sorsfinance.entities.Contact;
import com.vili.sorsfinance.entities.Country;
import com.vili.sorsfinance.entities.Email;
import com.vili.sorsfinance.entities.Person;
import com.vili.sorsfinance.entities.Phone;
import com.vili.sorsfinance.entities.State;
import com.vili.sorsfinance.entities.Wallet;
import com.vili.sorsfinance.entities.enums.AccountStatus;
import com.vili.sorsfinance.entities.enums.AccountType;
import com.vili.sorsfinance.entities.enums.CategoryType;
import com.vili.sorsfinance.entities.enums.ContactType;
import com.vili.sorsfinance.entities.enums.PeriodUnit;
import com.vili.sorsfinance.entities.enums.PersonProfile;
import com.vili.sorsfinance.entities.enums.PersonType;
import com.vili.sorsfinance.entities.enums.PhoneType;
import com.vili.sorsfinance.repositories.AccountRepository;
import com.vili.sorsfinance.repositories.AddressRepository;
import com.vili.sorsfinance.repositories.BranchRepository;
import com.vili.sorsfinance.repositories.CategoryRepository;
import com.vili.sorsfinance.repositories.CityRepository;
import com.vili.sorsfinance.repositories.ContactRepository;
import com.vili.sorsfinance.repositories.CountryRepository;
import com.vili.sorsfinance.repositories.EmailRepository;
import com.vili.sorsfinance.repositories.PersonRepository;
import com.vili.sorsfinance.repositories.PhoneRepository;
import com.vili.sorsfinance.repositories.StateRepository;

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

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Lazer", 1, CategoryType.TRANSACTION);
		Category cat2 = new Category(null, "Viagem", 2, CategoryType.TRANSACTION);
		Category cat3 = new Category(null, "Hospedagem", 3, CategoryType.TRANSACTION);
		Category cat4 = new Category(null, "Educação", 1, CategoryType.TRANSACTION);
		Category cat5 = new Category(null, "Curso", 2, CategoryType.TRANSACTION);
		
		cat1.addChild(cat2);
		cat2.addChild(cat3);
		cat4.addChild(cat5);
		
		cat2.setParent(cat1);
		cat3.setParent(cat2);
		cat5.setParent(cat4);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		
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
		Address adr1 = new Address(null, "Rua João Ferragut", "235", "Torre 7, apto 21", "Pinheirinho", "13286-000", cty3, true);
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
		Branch bch2 = new Branch(null, "Restaurante");
		Branch bch3 = new Branch(null, "Maquiagens");
		Branch bch4 = new Branch(null, "Banco");
		
		branchRepository.saveAll(Arrays.asList(bch1, bch2, bch3, bch4));
				
		Person p1 = new Person(null, "Pietro Magalhães Liguori", "36872371846", PersonType.NATURAL_PERSON, PersonProfile.HOLDER);
		Person p2 = new Person(null, "Santander", null, PersonType.LEGAL_PERSON, PersonProfile.BANK);
		
		p1.setContact(ctc1);
		p1.setBranch(bch1);
		p2.setBranch(bch4);

		personRepository.saveAll(Arrays.asList(p1, p2));
		
		Account acc1 = new Wallet(null, p1, "Carteira Pietro", 0.0, AccountType.WALLET, AccountStatus.ACTIVE);
		Account acc2 = new BankAccount(null, p1, "01013389-4", "0216", p2, 0.0, 1800.0, 0.08, PeriodUnit.MONTH, 10, PeriodUnit.DAY, 2800.0, AccountType.CHECKING_ACCOUNT, AccountStatus.ACTIVE);

		
		accountRepository.saveAll(Arrays.asList(acc1, acc2));
	}
	
	
}
