package com.fss.openbanking.utils;

import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * This is a Utility class for generating unique 12 digit number for 10 years.
 *   
 * @author Dinakar K
 * @version 1.0 - 03/07/2009
 * 
 *
 */
public class RRN {
	
	private static int changeOver = 100;
	
	private static int max = 100000000;

	private static int base = max/changeOver;
	
	private static long lastTime = 0;
	
	private static int count = 0;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/**
	 * To Generate 12 digit RRN. 
	 * Digit 1 : Last digit of an year.
	 * Digit 2-4 : Number of days in a year.
	 * Digit 5-12 : Random number.
	 * 
	 * @return rrn - String.
	 * 
	 */
	
	public static String genRRN(){
		StringBuffer buffer = new StringBuffer();
		synchronized (RRN.class) {
			GregorianCalendar currentGC = new GregorianCalendar();
			long currentTimeInMS = currentGC.getTimeInMillis();
			int day = currentGC.get(GregorianCalendar.DAY_OF_YEAR);
			
			buffer.append(String.valueOf(currentGC.get(GregorianCalendar.YEAR))
					.substring(3));
			String dayStr = String.valueOf(day); 				
			while(dayStr.length() < 3){
				dayStr = "0" + dayStr;
			}

			buffer.append(dayStr);
			String first = "";			
			if(currentTimeInMS != lastTime){
				if((lastTime + count) >= currentTimeInMS){
					try {
						long tmp = (lastTime + count + 1) - currentTimeInMS;
						Thread.sleep(tmp);
						currentTimeInMS += tmp;
					} catch (InterruptedException e) {
					}
				}
				lastTime = currentTimeInMS;
				count = 1;
				first = String.valueOf(currentTimeInMS + count).substring(String.valueOf(currentTimeInMS + count).length() - 8);				
			}else{
				count ++;
				first = String.valueOf(currentTimeInMS + count).substring(String.valueOf(currentTimeInMS + count).length() - 8);
			}			
			Integer rrnL = Integer.valueOf(first);				
			int current = rrnL/changeOver;
			int mod = rrnL%changeOver;
			int next = 0;			
			if(current % 2 == 0){
				next = base - (current/2 + 1);
			}else{
				next = current/2;
			}		
			int rrnInt = (next  * changeOver)  + mod; 
			String rrnStr = String.valueOf(rrnInt);
			if(rrnStr.length() < 8){
				while(rrnStr.length() < 8){
					rrnStr = "0" + rrnStr;
				}
			}
			buffer.append(rrnStr);
			try {
				Thread.sleep(2L);
			} catch (InterruptedException e) {				
			}
		}
		if(buffer.length() != 12){
			//ADMLogger.logTrace("Lenth is not 12 : " + buffer);
			return genRRN();
		}else{
			//ADMLogger.logTrace("*********************************** Generated RRN : " + buffer.toString() + " ************************************");
			return buffer.toString();
		}
	}	
	
	public static String RRN6()
	{
		/*r=new Random();
		String randomString="";
		int n = (int) (000000 + r.nextFloat()* 900000);
		randomString=String.valueOf(n);
		return randomString;*/
		
		return rrn();
	}
	
	public static String rrn()
	{
		Random rng = new Random(); // But use one instance throughout your app
		BigInteger current = BigInteger.ZERO;
		BigInteger big = new BigInteger("99999");
		for (int i = 0; i < 6; i++) {
		    BigInteger nextDigit = BigInteger.valueOf(rng.nextInt(10));
		    current = current.multiply(BigInteger.TEN).add(nextDigit);
		}
		if(current.subtract(big).signum() != 1)
		{
			current = null;
			big = null;
			return RRN.rrn();
		}
		else
			return current + "";
	}
	
	public static void main(String[] args) {
		System.out.println(genRRN());
	}
	
	

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING
					.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
