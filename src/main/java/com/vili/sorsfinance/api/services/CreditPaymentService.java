package com.vili.sorsfinance.api.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.CreditCard;
import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.api.domain.CreditInstallment;
import com.vili.sorsfinance.api.domain.CreditPayment;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.exceptions.custom.CastingException;
import com.vili.sorsfinance.framework.services.IService;
import com.vili.util.enums.DateParse;
import com.vili.util.enums.DateParse.Extract;

@Service
@EntityRef(CreditPayment.class)
public class CreditPaymentService implements IService {

	@Autowired
	CreditCardService cardService;
	@Autowired
	CreditCardStatementService statementService;
	@Autowired
	CreditInstallmentService installmentService;

	@Override
	public IEntity save(IEntity entity) {
		CreditPayment pay = (CreditPayment) entity;
		pay = (CreditPayment) IService.super.save(pay);

		CreditCard card = (CreditCard) cardService.findById(pay.getCard().getId());
		BankAccount acc = (BankAccount) card.getAccount();
		Integer closingDay = card.getClosingDay() == null ? 1 : card.getClosingDay();
		Integer grace = card.getGracePeriod() == null ? 0 : card.getGracePeriod();
		Calendar cal = resolveClosingDate(pay.getTransaction().getDate(), closingDay);
		List<Double> installmentValues = pay.getInstallmentValues(null);

		for (int i = 1; i <= installmentValues.size(); i++) {
			Date closingDate = cal.getTime();
			cal.add(Calendar.DATE, grace);
			Date dueDate = cal.getTime();
			String description = buildStatementDescription(closingDate);
			CreditCardStatement statement = statementService.findStatement(acc, description);

			if (statement == null) {
				CreditCardStatement temp = new CreditCardStatement(null, acc, description, closingDate, dueDate);
				statement = (CreditCardStatement) statementService.save(temp);
			}

			CreditInstallment installment = new CreditInstallment(null, pay, statement, installmentValues.get(i - 1), i);
			installment = (CreditInstallment) installmentService.save(installment);

			cal.setTime(closingDate);
			cal.add(Calendar.MONTH, 1);
			cal = resolveClosingDate(cal.getTime(), closingDay);
		}

		return pay;
	}

	private Calendar resolveClosingDate(Date date, Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		String lastDay = Extract.DAY.from(DateParse.MONTH.getEnd(date));

		try {
			cal.set(Calendar.DATE, day > Integer.parseInt(lastDay) ? Integer.parseInt(lastDay) : day);
		} catch (NumberFormatException e) {
			throw new CastingException("Cannot cast '" + lastDay + "' to Integer");
		}

		return cal;
	}

	private String buildStatementDescription(Date closingDate) {
		String month = Extract.MONTH_NAME.from(closingDate);
		month = month.substring(0, 1).toUpperCase() + month.substring(1, month.length());
		return month + " " + Extract.YEAR.from(closingDate);
	}
}
