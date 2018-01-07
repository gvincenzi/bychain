package org.byochain.api.request;

/**
 * UserEnablingRequest used for API Service "PUT /byochain/users"
 * @author Giuseppe Vincenzi
 *
 */
public class UserUpdateRequest {
	private Boolean enable;

	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	
}
