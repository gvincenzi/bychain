package org.byochain.api.response;

/**
 * BlockChainApiResponse
 * @author Giuseppe Vincenzi
 *
 */
public class BlockChainApiResponse {
	private String message;
	private Integer code;
	private Object data;
	
	public BlockChainApiResponse(Integer code, String message){
		setCode(code);
		setMessage(message);
	}
	
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

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
