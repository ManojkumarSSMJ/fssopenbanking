package com.fss.openbanking.constants;

public class TppConstants {
	
	public static String headerAuthorization = "Authorization";
	public static String yapilyUrl = "https://api.yapily.com";
	public static String callBackUrl = "http://localhost:8091/fssopenbanking/redirectPage";
	public static String paymentType = "DOMESTIC_PAYMENT";
	
	public static String payAuthUrl = "payment-auth-requests";
	public static String paymentUrl = "payments";
	public static String accAuthUrl = "account-auth-requests";
	public static String accountsUrl = "accounts";
	public static String balancesUrl = "balances";
	public static String transactionsUrl = "transactions";
	public static String directDebitsUrl = "direct-debits";
	public static String periodicPaymentsUrl = "periodic-payments";
	public static String institutionsUrl = "institutions";
	public static String createuserUrl = "users";
	
	
	public static String success = "C";
	public static String successCode = "000";
	public static String failure = "F";
	public static String failureCode = "001";
	public static String timeout = "T";
	public static String timeoutCode = "008";
	public static String timeoutMsg = "Timeout";
	public static String exception = "Exception";
	public static String exceptionCode = "002";
	
	

}
