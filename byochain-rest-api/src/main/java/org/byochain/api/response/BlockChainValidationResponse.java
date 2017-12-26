package org.byochain.api.response;

/**
 * BlockChainValidationResponse
 * @author Giuseppe Vincenzi
 *
 */
public class BlockChainValidationResponse {
	private String message;
	private Boolean result;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
}
