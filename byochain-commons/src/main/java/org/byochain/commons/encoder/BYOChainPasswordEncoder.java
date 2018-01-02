package org.byochain.commons.encoder;

import org.apache.commons.lang3.RandomStringUtils;
import org.byochain.commons.utils.BlockchainUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * BYOChainPasswordEncoder
 * @author Giuseppe Vincenzi
 *
 */
public class BYOChainPasswordEncoder implements PasswordEncoder {
	private static final int PASSWORD_LENGTH = 15;
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$";
	
	public String generateTemporaryPassword(){
		return RandomStringUtils.random(PASSWORD_LENGTH, CHARS);
	}
	
	@Override
	public String encode(CharSequence charSequence) {
		final StringBuilder sb = new StringBuilder(charSequence.length());
		sb.append(charSequence);
		return BlockchainUtils.applySha256(sb.toString());
	}

	@Override
	public boolean matches(CharSequence charSequence, String passwordToTest) {
		final StringBuilder sb = new StringBuilder(charSequence.length());
		sb.append(charSequence);
		return passwordToTest.equals(BlockchainUtils.applySha256(sb.toString()));
	}

}
