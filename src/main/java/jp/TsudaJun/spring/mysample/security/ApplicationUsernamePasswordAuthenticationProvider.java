package jp.TsudaJun.spring.mysample.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ApplicationUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         Identify user
        final String username = authentication.getName();
        
//         Create authentication token
//        ※認証されたユーザにUSERロールを付与する。
        return new UsernamePasswordAuthenticationToken(username, authentication.getCredentials(), 
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
