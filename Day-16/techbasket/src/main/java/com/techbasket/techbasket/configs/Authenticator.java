package com.techbasket.techbasket.configs;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class Authenticator extends AbstractAuthenticationToken {

  public Authenticator(Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    //     main worker
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
