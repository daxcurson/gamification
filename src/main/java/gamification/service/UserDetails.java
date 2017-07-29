package gamification.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;

import gamification.model.Group;

public interface UserDetails extends Serializable 
{
    Collection<GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
	Group getGroup();
}