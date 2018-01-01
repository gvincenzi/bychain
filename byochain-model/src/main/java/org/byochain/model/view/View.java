package org.byochain.model.view;

/**
 * View to filter contextually objects serialized to the HTTP response body. 
 * @author Giuseppe Vincenzi
 *
 */
public class View {
	public interface User {}
	public interface UserWithRoles extends User {}
	public interface BYODataException {}
}
