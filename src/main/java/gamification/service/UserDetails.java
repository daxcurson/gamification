package gamification.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;

import gamification.model.Group;
import gamification.model.User;

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
	User getUser();
}