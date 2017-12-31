package org.byochain.services.exception;

import org.byochain.commons.exception.ByoChainException;

/**
 * ByoChainServiceException
 * @author Giuseppe Vincenzi
 *
 */
public class ByoChainServiceException extends ByoChainException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3994114088237135634L;

	public ByoChainServiceException(String message) {
		super(message);
	}
	
}
