package com.vili.sorsfinance.api.entities.filters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Transaction;
import com.vili.sorsfinance.api.exceptions.InvalidRequestParamException;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.TransactionRepository;
import com.vili.util.enums.DateParse;

public class TransactionFilter extends EntityFilter<Transaction> {

	public TransactionFilter(Request request, TransactionRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Transaction> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		TransactionRepository repository = (TransactionRepository) getRepository();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (filter != null) {
			boolean hasCard = filter.get("card") != null;
			boolean hasAccount = filter.get("account") != null;
			boolean hasRecipient = filter.get("recipient") != null;
			boolean hasResponsible = filter.get("responsible") != null;
			boolean hasCategory = filter.get("category") != null;
			boolean hasType = filter.get("type") != null;
			boolean hasDate = filter.get("date") != null;
			boolean hasPeriod = filter.get("dateStart") != null && filter.get("dateEnd") != null && !hasDate;

			if (!hasCard) {
				if (!hasAccount) {
					if (!hasDate && !hasPeriod) {
						if (hasRecipient && hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndType(recipient,
									responsible, category, type, pageable);
						}

						if (hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesId(recipient,
									responsible, category, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByRecipientIdAndPaymentsResponsibleIdAndType(recipient, responsible,
									type, pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByRecipientIdAndCategoriesIdAndType(recipient, category, type,
									pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsResponsibleIdAndCategoriesIdAndType(responsible, category,
									type, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							return repository.findByRecipientIdAndPaymentsResponsibleId(recipient, responsible,
									pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							return repository.findByRecipientIdAndCategoriesId(recipient, category, pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsResponsibleIdAndCategoriesId(responsible, category,
									pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Integer type = (Integer) filter.get("type");
							return repository.findByRecipientIdAndType(recipient, type, pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsResponsibleIdAndType(responsible, type, pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByCategoriesIdAndType(category, type, pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							return repository.findByRecipientId(recipient, pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							return repository.findByPaymentsResponsibleId(responsible, pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long category = (Long) filter.get("category");
							return repository.findByCategoriesId(category, pageable);
						}

						if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Integer type = (Integer) filter.get("type");
							return repository.findByType(type, pageable);
						}
					} else {
						if (hasPeriod) {
							String date = null;
							Date dateStart;
							Date dateEnd;

							try {
								date = (String) filter.get("dateStart");
								dateStart = DateParse.DAY.getStart(sdf.parse(date));
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: dateStart="
										+ date + ". Must match '" + sdf.toPattern() + "'");
							}

							try {
								date = (String) filter.get("dateEnd");
								dateEnd = DateParse.DAY.getEnd(sdf.parse(date));
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: dateEnd="
										+ date + ". Must match '" + sdf.toPattern() + "'");
							}

							if (hasRecipient && hasResponsible && hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository
										.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
												recipient, responsible, category, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && hasResponsible && hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
										recipient, responsible, category, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && hasResponsible && !hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Integer type = (Integer) filter.get("type");
								return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(recipient, responsible, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && !hasResponsible && hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByRecipientIdAndCategoriesIdAndDateBetweenAndType(recipient,
										category, dateStart, dateEnd, type, pageable);
							}

							if (!hasRecipient && hasResponsible && hasCategory && hasType) {
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(responsible, category, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBetween(recipient, responsible, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long category = (Long) filter.get("category");
								return repository.findByRecipientIdAndCategoriesIdAndDateBetween(recipient,
										category, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBetween(responsible, category, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Integer type = (Integer) filter.get("type");
								return repository.findByRecipientIdAndDateBetweenAndType(recipient, dateStart, dateEnd, type,
										pageable);
							}

							if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
								Long responsible = (Long) filter.get("responsible");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsResponsibleIdAndDateBetweenAndType(responsible,
										dateStart, dateEnd, type, pageable);
							}

							if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByCategoriesIdAndDateBetweenAndType(category, dateStart, dateEnd, type,
										pageable);
							}

							if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								return repository.findByRecipientIdAndDateBetween(recipient, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
								Long responsible = (Long) filter.get("responsible");
								return repository.findByPaymentsResponsibleIdAndDateBetween(responsible, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
								Long category = (Long) filter.get("category");
								return repository.findByCategoriesIdAndDateBetween(category, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
								Integer type = (Integer) filter.get("type");
								return repository.findByDateBetweenAndType(dateStart, dateEnd, type, pageable);
							}

							return repository.findByDateBetween(dateStart, dateEnd, pageable);
						} else {
							String comp = (String) filter.get("date");
							String pattern = "^(<|>)*[0-9]{4}(-[0-9]{2}){2}$";

							if (!comp.matches(pattern)) {
								throw new InvalidRequestParamException("Request parameter must match '" + pattern
										+ "'. Received value: date=" + comp);
							}

							boolean hasOperand = List.of("<", ">").contains(comp.substring(0, 1));

							if (hasOperand) {
								try {
									String operand = comp.substring(0, 1);
									Date date = sdf.parse(comp.substring(1));
									Date dateStart = DateParse.DAY.getStart(date);
									Date dateEnd = DateParse.DAY.getEnd(date);

									if (operand.indexOf("<") == 0) {
										if (hasRecipient && hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository
													.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(recipient, responsible, category, dateStart, type, pageable);
										}

										if (hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBefore(
													recipient, responsible, category, dateStart, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBeforeAndType(recipient, responsible, dateStart, type, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndCategoriesIdAndDateBeforeAndType(recipient,
													category, dateStart, type, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(responsible, category, dateStart, type, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBefore(recipient,
													responsible, dateStart, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											return repository.findByRecipientIdAndCategoriesIdAndDateBefore(recipient, category,
													dateStart, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBefore(responsible,
													category, dateStart, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndDateBeforeAndType(recipient, dateStart, type, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsResponsibleIdAndDateBeforeAndType(responsible, dateStart, type,
													pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByCategoriesIdAndDateBeforeAndType(category, dateStart, type, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											return repository.findByRecipientIdAndDateBefore(recipient, dateStart, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsResponsibleIdAndDateBefore(responsible, dateStart, pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long category = (Long) filter.get("category");
											return repository.findByCategoriesIdAndDateBefore(category, dateStart, pageable);
										}

										if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Integer type = (Integer) filter.get("type");
											return repository.findByDateBeforeAndType(dateStart, type, pageable);
										}

										return repository.findByDateBefore(dateStart, pageable);
									} else if (operand.indexOf(">") == 0) {
										if (hasRecipient && hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository
													.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(recipient, responsible, category, dateEnd, type, pageable);
										}

										if (hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfter(recipient, responsible, category, dateEnd, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateAfterAndType(recipient, responsible, dateEnd, type, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndCategoriesIdAndDateAfterAndType(recipient,
													category, dateEnd, type, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(responsible, category, dateEnd, type, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateAfter(recipient,
													responsible, dateEnd, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											return repository.findByRecipientIdAndCategoriesIdAndDateAfter(recipient, category,
													dateEnd, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateAfter(responsible,
													category, dateEnd, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Integer type = (Integer) filter.get("type");
											return repository.findByRecipientIdAndDateAfterAndType(recipient, dateEnd, type, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsResponsibleIdAndDateAfterAndType(responsible, dateEnd, type,
													pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByCategoriesIdAndDateAfterAndType(category, dateEnd, type, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											return repository.findByRecipientIdAndDateAfter(recipient, dateEnd, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsResponsibleIdAndDateAfter(responsible, dateEnd, pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long category = (Long) filter.get("category");
											return repository.findByCategoriesIdAndDateAfter(category, dateEnd, pageable);
										}

										if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Integer type = (Integer) filter.get("type");
											return repository.findByDateAfterAndType(dateEnd, type, pageable);
										}

										return repository.findByDateAfter(dateEnd, pageable);
									}

									throw new InvalidRequestParamException("Date operand: '" + operand + "' is invalid.");
								} catch (ParseException e) {
									throw new InvalidRequestParamException("Cannot parse to Date: date="
											+ comp.substring(1) + ". Must match '" + sdf.toPattern() + "'");
								}
							} else {
								try {
									Date date = sdf.parse(comp);
									Date dateStart = DateParse.DAY.getStart(date);
									Date dateEnd = DateParse.DAY.getEnd(date);

									if (hasRecipient && hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository
												.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
														recipient, responsible, category, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
												recipient, responsible, category, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(recipient, responsible, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByRecipientIdAndCategoriesIdAndDateBetweenAndType(recipient,
												category, dateStart, dateEnd, type, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(responsible, category, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										return repository.findByRecipientIdAndPaymentsResponsibleIdAndDateBetween(recipient, responsible, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										return repository.findByRecipientIdAndCategoriesIdAndDateBetween(recipient,
												category, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsResponsibleIdAndCategoriesIdAndDateBetween(responsible, category, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Integer type = (Integer) filter.get("type");
										return repository.findByRecipientIdAndDateBetweenAndType(recipient, dateStart, dateEnd, type,
												pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsResponsibleIdAndDateBetweenAndType(responsible,
												dateStart, dateEnd, type, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByCategoriesIdAndDateBetweenAndType(category, dateStart, dateEnd, type,
												pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										return repository.findByRecipientIdAndDateBetween(recipient, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsResponsibleIdAndDateBetween(responsible, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long category = (Long) filter.get("category");
										return repository.findByCategoriesIdAndDateBetween(category, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Integer type = (Integer) filter.get("type");
										return repository.findByDateBetweenAndType(dateStart, dateEnd, type, pageable);
									}

									return repository.findByDateBetween(dateStart, dateEnd, pageable);
								} catch (ParseException e) {
									throw new InvalidRequestParamException("Cannot parse to Date: date="
											+ comp + ". Must match '" + sdf.toPattern() + "'");
								}
							}
						}
					}
				} else {
					Long account = (Long) filter.get("account");

					if (!hasDate && !hasPeriod) {
						if (hasRecipient && hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository
									.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndType(
											account, recipient, responsible, category, type, pageable);
						}

						if (hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository
									.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesId(
											account, recipient, responsible, category, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndType(
									account, recipient, responsible, type, pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndType(account,
									recipient, category, type, pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndType(
									account, responsible, category, type, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleId(account,
									recipient, responsible, pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesId(account, recipient,
									category, pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesId(account,
									responsible, category, pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndRecipientIdAndType(account, recipient, type,
									pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndType(account,
									responsible, type, pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndCategoriesIdAndType(account, category, type,
									pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							return repository.findByPaymentsAccountIdAndRecipientId(account, recipient, pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							return repository.findByPaymentsAccountIdAndPaymentsResponsibleId(account, responsible,
									pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsAccountIdAndCategoriesId(account, category, pageable);
						}

						if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsAccountIdAndType(account, type, pageable);
						}
					} else {
						if (hasPeriod) {
							String date = null;
							Date dateStart;
							Date dateEnd;

							try {
								date = (String) filter.get("dateStart");
								dateStart = DateParse.DAY.getStart(sdf.parse(date));
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: dateStart="
										+ date + ". Must match '" + sdf.toPattern() + "'");
							}

							try {
								date = (String) filter.get("dateEnd");
								dateEnd = DateParse.DAY.getEnd(sdf.parse(date));
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: dateEnd="
										+ date + ". Must match '" + sdf.toPattern() + "'");
							}

							if (hasRecipient && hasResponsible && hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository
										.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
												account, recipient, responsible, category, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && hasResponsible && hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
										account, recipient, responsible, category, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && hasResponsible && !hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(account,
										recipient, responsible, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && !hasResponsible && hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBetweenAndType(account, recipient,
										category, dateStart, dateEnd, type, pageable);
							}

							if (!hasRecipient && hasResponsible && hasCategory && hasType) {
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(account,
										responsible, category, dateStart, dateEnd, type, pageable);
							}

							if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long responsible = (Long) filter.get("responsible");
								return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetween(account,
										recipient, responsible, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								Long category = (Long) filter.get("category");
								return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBetween(account, recipient,
										category, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
								Long responsible = (Long) filter.get("responsible");
								Long category = (Long) filter.get("category");
								return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(account,
										responsible, category, dateStart, dateEnd, pageable);
							}

							if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
								Long recipient = (Long) filter.get("recipient");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndRecipientIdAndDateBetweenAndType(account, recipient, dateStart, dateEnd, type,
										pageable);
							}

							if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
								Long responsible = (Long) filter.get("responsible");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBetweenAndType(account, responsible,
										dateStart, dateEnd, type, pageable);
							}

							if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
								Long category = (Long) filter.get("category");
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBetweenAndType(account, category, dateStart, dateEnd, type,
										pageable);
							}

							if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
								Long recipient = (Long) filter.get("recipient");
								return repository.findByPaymentsAccountIdAndRecipientIdAndDateBetween(account, recipient, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
								Long responsible = (Long) filter.get("responsible");
								return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBetween(account, responsible, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
								Long category = (Long) filter.get("category");
								return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBetween(account, category, dateStart, dateEnd, pageable);
							}

							if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
								Integer type = (Integer) filter.get("type");
								return repository.findByPaymentsAccountIdAndDateBetweenAndType(account, dateStart, dateEnd, type, pageable);
							}

							return repository.findByDateBetween(dateStart, dateEnd, pageable);
						} else {
							String comp = (String) filter.get("date");
							String pattern = "^(<|>)*[0-9]{4}(-[0-9]{2}){2}$";

							if (!comp.matches(pattern)) {
								throw new InvalidRequestParamException("Request parameter must match '" + pattern
										+ "'. Received value: date=" + comp);
							}

							boolean hasOperand = List.of("<", ">").contains(comp.substring(0, 1));

							if (hasOperand) {
								try {
									String operand = comp.substring(0, 1);
									Date date = sdf.parse(comp.substring(1));
									Date dateStart = DateParse.DAY.getStart(date);
									Date dateEnd = DateParse.DAY.getEnd(date);

									if (operand.indexOf("<") == 0) {
										if (hasRecipient && hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository
													.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(account,
															recipient, responsible, category, dateStart, type, pageable);
										}

										if (hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBefore(
													account, recipient, responsible, category, dateStart, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBeforeAndType(account,
													recipient, responsible, dateStart, type, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBeforeAndType(account, recipient,
													category, dateStart, type, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(account,
													responsible, category, dateStart, type, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBefore(account, recipient,
													responsible, dateStart, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBefore(account, recipient, category,
													dateStart, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBefore(account, responsible,
													category, dateStart, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndDateBeforeAndType(account, recipient, dateStart, type, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBeforeAndType(account, responsible, dateStart, type,
													pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBeforeAndType(account, category, dateStart, type, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											return repository.findByPaymentsAccountIdAndRecipientIdAndDateBefore(account, recipient, dateStart, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBefore(account, responsible, dateStart, pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBefore(account, category, dateStart, pageable);
										}

										if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndDateBeforeAndType(account, dateStart, type, pageable);
										}

										return repository.findByDateBefore(dateStart, pageable);
									} else if (operand.indexOf(">") == 0) {
										if (hasRecipient && hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository
													.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(account,
															recipient, responsible, category, dateEnd, type, pageable);
										}

										if (hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfter(
													account, recipient, responsible, category, dateEnd, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateAfterAndType(account,
													recipient, responsible, dateEnd, type, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateAfterAndType(account, recipient,
													category, dateEnd, type, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(account,
													responsible, category, dateEnd, type, pageable);
										}

										if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateAfter(account, recipient,
													responsible, dateEnd, pageable);
										}

										if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateAfter(account, recipient, category,
													dateEnd, pageable);
										}

										if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfter(account, responsible,
													category, dateEnd, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Long recipient = (Long) filter.get("recipient");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndRecipientIdAndDateAfterAndType(account, recipient, dateEnd, type, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
											Long responsible = (Long) filter.get("responsible");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateAfterAndType(account, responsible, dateEnd, type,
													pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
											Long category = (Long) filter.get("category");
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndCategoriesIdAndDateAfterAndType(account, category, dateEnd, type, pageable);
										}

										if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
											Long recipient = (Long) filter.get("recipient");
											return repository.findByPaymentsAccountIdAndRecipientIdAndDateAfter(account, recipient, dateEnd, pageable);
										}

										if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
											Long responsible = (Long) filter.get("responsible");
											return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateAfter(account, responsible, dateEnd, pageable);
										}

										if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
											Long category = (Long) filter.get("category");
											return repository.findByPaymentsAccountIdAndCategoriesIdAndDateAfter(account, category, dateEnd, pageable);
										}

										if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
											Integer type = (Integer) filter.get("type");
											return repository.findByPaymentsAccountIdAndDateAfterAndType(account, dateEnd, type, pageable);
										}

										return repository.findByDateAfter(dateEnd, pageable);
									}

									throw new InvalidRequestParamException(
											"Date operand: " + operand + "is invalid.");
								} catch (ParseException e) {
									throw new InvalidRequestParamException("Cannot parse to Date: date="
											+ comp.substring(1) + ". Must match '" + sdf.toPattern() + "'");
								}
							} else {
								try {
									Date date = sdf.parse(comp);
									Date dateStart = DateParse.DAY.getStart(date);
									Date dateEnd = DateParse.DAY.getEnd(date);

									if (hasRecipient && hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository
												.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
														account, recipient, responsible, category, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
												account, recipient, responsible, category, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(account,
												recipient, responsible, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBetweenAndType(account, recipient,
												category, dateStart, dateEnd, type, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(account,
												responsible, category, dateStart, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsAccountIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetween(account,
												recipient, responsible, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsAccountIdAndRecipientIdAndCategoriesIdAndDateBetween(account, recipient,
												category, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(account,
												responsible, category, dateStart, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndRecipientIdAndDateBetweenAndType(account, recipient, dateStart, dateEnd, type,
												pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBetweenAndType(account, responsible,
												dateStart, dateEnd, type, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBetweenAndType(account, category, dateStart, dateEnd, type,
												pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										return repository.findByPaymentsAccountIdAndRecipientIdAndDateBetween(account, recipient, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsAccountIdAndPaymentsResponsibleIdAndDateBetween(account, responsible, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsAccountIdAndCategoriesIdAndDateBetween(account, category, dateStart, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsAccountIdAndDateBetweenAndType(account, dateStart, dateEnd, type, pageable);
									}

									return repository.findByDateBetween(dateStart, dateEnd, pageable);
								} catch (ParseException e) {
									throw new InvalidRequestParamException("Cannot parse to Date: date="
											+ comp + ". Must match '" + sdf.toPattern() + "'");
								}
							}
						}
					}

					return repository.findByPaymentsAccountId(account, pageable);
				}
			} else {
				Long card = (Long) filter.get("card");

				if (!hasDate && !hasPeriod) {
					if (hasRecipient && hasResponsible && hasCategory && hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long responsible = (Long) filter.get("responsible");
						Long category = (Long) filter.get("category");
						Integer type = (Integer) filter.get("type");
						return repository
								.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndType(card,
										recipient, responsible, category, type, pageable);
					}

					if (hasRecipient && hasResponsible && hasCategory && !hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long responsible = (Long) filter.get("responsible");
						Long category = (Long) filter.get("category");
						return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesId(
								card, recipient, responsible, category, pageable);
					}

					if (hasRecipient && hasResponsible && !hasCategory && hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndType(card,
								recipient, responsible, type, pageable);
					}

					if (hasRecipient && !hasResponsible && hasCategory && hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long category = (Long) filter.get("category");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndType(card, recipient,
								category, type, pageable);
					}

					if (!hasRecipient && hasResponsible && hasCategory && hasType) {
						Long responsible = (Long) filter.get("responsible");
						Long category = (Long) filter.get("category");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndType(card,
								responsible, category, type, pageable);
					}

					if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long responsible = (Long) filter.get("responsible");
						return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleId(card, recipient,
								responsible, pageable);
					}

					if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
						Long recipient = (Long) filter.get("recipient");
						Long category = (Long) filter.get("category");
						return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesId(card, recipient, category,
								pageable);
					}

					if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
						Long responsible = (Long) filter.get("responsible");
						Long category = (Long) filter.get("category");
						return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesId(card, responsible,
								category, pageable);
					}

					if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
						Long recipient = (Long) filter.get("recipient");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndRecipientIdAndType(card, recipient, type, pageable);
					}

					if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndType(card, responsible, type,
								pageable);
					}

					if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
						Long category = (Long) filter.get("category");
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndCategoriesIdAndType(card, category, type, pageable);
					}

					if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
						Long recipient = (Long) filter.get("recipient");
						return repository.findByPaymentsCardIdAndRecipientId(card, recipient, pageable);
					}

					if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
						Long responsible = (Long) filter.get("responsible");
						return repository.findByPaymentsCardIdAndPaymentsResponsibleId(card, responsible, pageable);
					}

					if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
						Long category = (Long) filter.get("category");
						return repository.findByPaymentsCardIdAndCategoriesId(card, category, pageable);
					}

					if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
						Integer type = (Integer) filter.get("type");
						return repository.findByPaymentsCardIdAndType(card, type, pageable);
					}
				} else {
					if (hasPeriod) {
						String date = null;
						Date dateStart;
						Date dateEnd;

						try {
							date = (String) filter.get("dateStart");
							dateStart = DateParse.DAY.getStart(sdf.parse(date));
						} catch (ParseException e) {
							throw new InvalidRequestParamException("Cannot parse to Date: dateStart="
									+ date + ". Must match '" + sdf.toPattern() + "'");
						}

						try {
							date = (String) filter.get("dateEnd");
							dateEnd = DateParse.DAY.getEnd(sdf.parse(date));
						} catch (ParseException e) {
							throw new InvalidRequestParamException("Cannot parse to Date: dateEnd="
									+ date + ". Must match '" + sdf.toPattern() + "'");
						}

						if (hasRecipient && hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository
									.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
											card, recipient, responsible, category, dateStart, dateEnd, type, pageable);
						}

						if (hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
									card, recipient, responsible, category, dateStart, dateEnd, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(card,
									recipient, responsible, dateStart, dateEnd, type, pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBetweenAndType(card, recipient,
									category, dateStart, dateEnd, type, pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(card,
									responsible, category, dateStart, dateEnd, type, pageable);
						}

						if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long responsible = (Long) filter.get("responsible");
							return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetween(card,
									recipient, responsible, dateStart, dateEnd, pageable);
						}

						if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBetween(card, recipient,
									category, dateStart, dateEnd, pageable);
						}

						if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(card,
									responsible, category, dateStart, dateEnd, pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Long recipient = (Long) filter.get("recipient");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndRecipientIdAndDateBetweenAndType(card, recipient, dateStart, dateEnd, type,
									pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
							Long responsible = (Long) filter.get("responsible");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBetweenAndType(card, responsible,
									dateStart, dateEnd, type, pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
							Long category = (Long) filter.get("category");
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndCategoriesIdAndDateBetweenAndType(card, category, dateStart, dateEnd, type,
									pageable);
						}

						if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
							Long recipient = (Long) filter.get("recipient");
							return repository.findByPaymentsCardIdAndRecipientIdAndDateBetween(card, recipient, dateStart, dateEnd, pageable);
						}

						if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
							Long responsible = (Long) filter.get("responsible");
							return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBetween(card, responsible, dateStart, dateEnd, pageable);
						}

						if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
							Long category = (Long) filter.get("category");
							return repository.findByPaymentsCardIdAndCategoriesIdAndDateBetween(card, category, dateStart, dateEnd, pageable);
						}

						if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
							Integer type = (Integer) filter.get("type");
							return repository.findByPaymentsCardIdAndDateBetweenAndType(card, dateStart, dateEnd, type, pageable);
						}

						return repository.findByDateBetween(dateStart, dateEnd, pageable);
					} else {
						String comp = (String) filter.get("date");
						String pattern = "^(<|>)*[0-9]{4}(-[0-9]{2}){2}$";

						if (!comp.matches(pattern)) {
							throw new InvalidRequestParamException("Request parameter must match '" + pattern
									+ "'. Received value: date=" + comp);
						}

						boolean hasOperand = List.of("<", ">").contains(comp.substring(0, 1));

						if (hasOperand) {
							try {
								String operand = comp.substring(0, 1);
								Date date = sdf.parse(comp.substring(1));
								Date dateStart = DateParse.DAY.getStart(date);
								Date dateEnd = DateParse.DAY.getEnd(date);

								if (operand.indexOf("<") == 0) {
									if (hasRecipient && hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository
												.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(card,
														recipient, responsible, category, dateStart, type, pageable);
									}

									if (hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBefore(
												card, recipient, responsible, category, dateStart, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBeforeAndType(card,
												recipient, responsible, dateStart, type, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBeforeAndType(card, recipient,
												category, dateStart, type, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBeforeAndType(card,
												responsible, category, dateStart, type, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBefore(card, recipient,
												responsible, dateStart, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBefore(card, recipient, category,
												dateStart, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBefore(card, responsible,
												category, dateStart, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndDateBeforeAndType(card, recipient, dateStart, type, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBeforeAndType(card, responsible, dateStart, type,
												pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndCategoriesIdAndDateBeforeAndType(card, category, dateStart, type, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										return repository.findByPaymentsCardIdAndRecipientIdAndDateBefore(card, recipient, dateStart, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBefore(card, responsible, dateStart, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndCategoriesIdAndDateBefore(card, category, dateStart, pageable);
									}

									if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndDateBeforeAndType(card, dateStart, type, pageable);
									}

									return repository.findByDateBefore(dateStart, pageable);
								} else if (operand.indexOf(">") == 0) {
									if (hasRecipient && hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository
												.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(card,
														recipient, responsible, category, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfter(
												card, recipient, responsible, category, dateEnd, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateAfterAndType(card,
												recipient, responsible, dateEnd, type, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateAfterAndType(card, recipient,
												category, dateEnd, type, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfterAndType(card,
												responsible, category, dateEnd, type, pageable);
									}

									if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateAfter(card, recipient,
												responsible, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateAfter(card, recipient, category,
												dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateAfter(card, responsible,
												category, dateEnd, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Long recipient = (Long) filter.get("recipient");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndRecipientIdAndDateAfterAndType(card, recipient, dateEnd, type, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
										Long responsible = (Long) filter.get("responsible");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateAfterAndType(card, responsible, dateEnd, type,
												pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
										Long category = (Long) filter.get("category");
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndCategoriesIdAndDateAfterAndType(card, category, dateEnd, type, pageable);
									}

									if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
										Long recipient = (Long) filter.get("recipient");
										return repository.findByPaymentsCardIdAndRecipientIdAndDateAfter(card, recipient, dateEnd, pageable);
									}

									if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
										Long responsible = (Long) filter.get("responsible");
										return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateAfter(card, responsible, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
										Long category = (Long) filter.get("category");
										return repository.findByPaymentsCardIdAndCategoriesIdAndDateAfter(card, category, dateEnd, pageable);
									}

									if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
										Integer type = (Integer) filter.get("type");
										return repository.findByPaymentsCardIdAndDateAfterAndType(card, dateEnd, type, pageable);
									}

									return repository.findByDateAfter(dateEnd, pageable);
								}

								throw new InvalidRequestParamException(
										"Date operand: " + operand + "is invalid.");
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: date="
										+ comp.substring(1) + ". Must match '" + sdf.toPattern() + "'");
							}
						} else {
							try {
								Date date = sdf.parse(comp);
								Date dateStart = DateParse.DAY.getStart(date);
								Date dateEnd = DateParse.DAY.getEnd(date);

								if (hasRecipient && hasResponsible && hasCategory && hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long responsible = (Long) filter.get("responsible");
									Long category = (Long) filter.get("category");
									Integer type = (Integer) filter.get("type");
									return repository
											.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(
													card, recipient, responsible, category, dateStart, dateEnd, type, pageable);
								}

								if (hasRecipient && hasResponsible && hasCategory && !hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long responsible = (Long) filter.get("responsible");
									Long category = (Long) filter.get("category");
									return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(
											card, recipient, responsible, category, dateStart, dateEnd, pageable);
								}

								if (hasRecipient && hasResponsible && !hasCategory && hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long responsible = (Long) filter.get("responsible");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetweenAndType(card,
											recipient, responsible, dateStart, dateEnd, type, pageable);
								}

								if (hasRecipient && !hasResponsible && hasCategory && hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long category = (Long) filter.get("category");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBetweenAndType(card, recipient,
											category, dateStart, dateEnd, type, pageable);
								}

								if (!hasRecipient && hasResponsible && hasCategory && hasType) {
									Long responsible = (Long) filter.get("responsible");
									Long category = (Long) filter.get("category");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetweenAndType(card,
											responsible, category, dateStart, dateEnd, type, pageable);
								}

								if (hasRecipient && hasResponsible && !hasCategory && !hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long responsible = (Long) filter.get("responsible");
									return repository.findByPaymentsCardIdAndRecipientIdAndPaymentsResponsibleIdAndDateBetween(card,
											recipient, responsible, dateStart, dateEnd, pageable);
								}

								if (hasRecipient && !hasResponsible && hasCategory && !hasType) {
									Long recipient = (Long) filter.get("recipient");
									Long category = (Long) filter.get("category");
									return repository.findByPaymentsCardIdAndRecipientIdAndCategoriesIdAndDateBetween(card, recipient,
											category, dateStart, dateEnd, pageable);
								}

								if (!hasRecipient && hasResponsible && hasCategory && !hasType) {
									Long responsible = (Long) filter.get("responsible");
									Long category = (Long) filter.get("category");
									return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndCategoriesIdAndDateBetween(card,
											responsible, category, dateStart, dateEnd, pageable);
								}

								if (hasRecipient && !hasResponsible && !hasCategory && hasType) {
									Long recipient = (Long) filter.get("recipient");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndRecipientIdAndDateBetweenAndType(card, recipient, dateStart, dateEnd, type,
											pageable);
								}

								if (!hasRecipient && hasResponsible && !hasCategory && hasType) {
									Long responsible = (Long) filter.get("responsible");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBetweenAndType(card, responsible,
											dateStart, dateEnd, type, pageable);
								}

								if (!hasRecipient && !hasResponsible && hasCategory && hasType) {
									Long category = (Long) filter.get("category");
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndCategoriesIdAndDateBetweenAndType(card, category, dateStart, dateEnd, type,
											pageable);
								}

								if (hasRecipient && !hasResponsible && !hasCategory && !hasType) {
									Long recipient = (Long) filter.get("recipient");
									return repository.findByPaymentsCardIdAndRecipientIdAndDateBetween(card, recipient, dateStart, dateEnd, pageable);
								}

								if (!hasRecipient && hasResponsible && !hasCategory && !hasType) {
									Long responsible = (Long) filter.get("responsible");
									return repository.findByPaymentsCardIdAndPaymentsResponsibleIdAndDateBetween(card, responsible, dateStart, dateEnd, pageable);
								}

								if (!hasRecipient && !hasResponsible && hasCategory && !hasType) {
									Long category = (Long) filter.get("category");
									return repository.findByPaymentsCardIdAndCategoriesIdAndDateBetween(card, category, dateStart, dateEnd, pageable);
								}

								if (!hasRecipient && !hasResponsible && !hasCategory && hasType) {
									Integer type = (Integer) filter.get("type");
									return repository.findByPaymentsCardIdAndDateBetweenAndType(card, dateStart, dateEnd, type, pageable);
								}

								return repository.findByDateBetween(dateStart, dateEnd, pageable);
							} catch (ParseException e) {
								throw new InvalidRequestParamException("Cannot parse to Date: date="
										+ comp + ". Must match '" + sdf.toPattern() + "'");
							}
						}
					}
				}

				return repository.findByPaymentsCardId(card, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
