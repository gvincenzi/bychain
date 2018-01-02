package org.byochain.commons.exception;

/**
 * ByoChainException
 * @author Giuseppe Vincenzi
 *
 */
public class ByoChainException extends Exception{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7762665561495822428L;

	public ByoChainException(){
		super();
	}
	
	public ByoChainException(String message){
		super(message);
	}
	
	public ByoChainException(String message, Throwable e){
		super(message,e);
	}
}
