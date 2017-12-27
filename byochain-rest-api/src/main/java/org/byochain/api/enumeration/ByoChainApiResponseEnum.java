package org.byochain.api.enumeration;

import java.util.Locale;

import org.byochain.api.response.BlockChainApiResponse;
import org.springframework.context.MessageSource;

/**
 * ByoChainApiResponseEnum
 * @author Giuseppe Vincenzi
 *
 */
public enum ByoChainApiResponseEnum {
	BLOCK_CONTROLLER_VALIDATION_OK("block.controller.validation.ok"),
	BLOCK_CONTROLLER_OK("block.controller.ok");

	private static final String MESSAGE = ".message";
	private static final String CODE = ".code";
	private String key;

	ByoChainApiResponseEnum(String key) {
		this.key = key;
	}

	public BlockChainApiResponse getResponse(MessageSource messageSource,
			Locale locale, Object... args) {
		BlockChainApiResponse apiResponse = new BlockChainApiResponse(
				Integer.parseInt(messageSource.getMessage(this.key + CODE, null, locale)),
				messageSource.getMessage(this.key + MESSAGE, args, locale));
		return apiResponse;
	}
}
