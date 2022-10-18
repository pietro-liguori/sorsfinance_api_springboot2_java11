package com.vili.sorsfinance.api.validation.utils;

public class BR {

	private static final int[] weightCpf = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] weightCnpj = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	private static int calculate(final String str, final int[] weight) {
		int sum = 0;
		
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}
	
	public static boolean isValidCPF(final String number) {
		if ((number == null) || (number.length() != 11) || number.matches(number.charAt(0) + "{11}"))
			return false;
		
		final Integer digit1 = calculate(number.substring(0, 9), weightCpf);
		final Integer digit2 = calculate(number.substring(0, 9) + digit1, weightCpf);
		return number.equals(number.substring(0, 9) + digit1.toString() + digit2.toString());
	}
	
	public static boolean isValidCNPJ(final String number) {
		if ((number == null) || (number.length() != 14) || number.matches(number.charAt(0) + "{14}"))
			return false;
		
		final Integer digit1 = calculate(number.substring(0, 12), weightCnpj);
		final Integer digit2 = calculate(number.substring(0, 12) + digit1, weightCnpj);
		return number.equals(number.substring(0, 12) + digit1.toString() + digit2.toString());
	}
}
