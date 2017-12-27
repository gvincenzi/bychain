package org.byochain.api.exception;

import org.byochain.api.serializer.ByoChainApiExceptionSerializer;
import org.byochain.commons.exception.ByoChainException;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ByoChainApiException
 * @author Giuseppe Vincenzi
 *
 */
@JsonSerialize(using = ByoChainApiExceptionSerializer.class)
public class ByoChainApiException extends ByoChainException {
	private Integer code;

	public ByoChainApiException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
	public ByoChainApiException(String message) {
		super(message);
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
}
