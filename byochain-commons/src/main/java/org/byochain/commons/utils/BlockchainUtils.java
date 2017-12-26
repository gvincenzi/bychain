package org.byochain.commons.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class BlockchainUtils {
	private static final String REGEX_DIGIT = "[0-9].*";

	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String calculateHash(String previousHash, Long timestamp, Integer token, String data) {
		String calculatedhash = applySha256( 
				previousHash +
				Long.toString(timestamp) +
				Integer.toString(token) +
				data 
				);
		return calculatedhash;
	}
	
	public static boolean isHashResolved(Integer difficultLevel, String hash) {
		List<Integer> digits = new ArrayList<Integer>(difficultLevel);
		
		Integer index = 0;
		while(index<hash.length() && digits.size()<difficultLevel){
			String s = hash.substring(index,++index);
			if(s.matches(REGEX_DIGIT)){
				digits.add(Integer.parseInt(s));
			}
		}

		Integer sum = digits.parallelStream().reduce(0, Integer::sum);
		return sum%difficultLevel == 0;
	}
}
