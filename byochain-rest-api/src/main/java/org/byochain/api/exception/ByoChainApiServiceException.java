package org.byochain.api.exception;

import org.byochain.api.serializer.ByoChainApiExceptionSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ByoChainApiServiceException
 * @author Giuseppe Vincenzi
 *
 */
@JsonSerialize(using = ByoChainApiExceptionSerializer.class)
public class ByoChainApiServiceException extends ByoChainApiException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3348688510635193629L;
	
	/**
	 * Code
	 */
	private Integer code;

	public ByoChainApiServiceException(Integer code, String message) {
		super(message);
		this.code = code;
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
