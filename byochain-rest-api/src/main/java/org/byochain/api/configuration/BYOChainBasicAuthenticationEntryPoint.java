package org.byochain.api.configuration;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * BYOChainBasicAuthenticationEntryPoint
 * @author Giuseppe Vincenzi
 *
 */
public class BYOChainBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{
	public static String REALM="BYOCHAIN_REST_API";
    
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }
}
