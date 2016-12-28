package gamification.service.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gamification.dao.UserDAO;
import gamification.model.User;

public class AuthenticationUserDetailsGetter implements UserDetailsService {
	private static Logger log=LogManager.getLogger(AuthenticationUserDetailsGetter.class);
    private UserDAO userRepository;

    //required by cglib because I use class based aspect weaving
    protected AuthenticationUserDetailsGetter() {
    }

    public AuthenticationUserDetailsGetter(UserDAO userRepository) {
    	log.trace("Estoy en AuthenticationUserDetailsGetter.constructor");
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	log.trace("Estoy en AuthenticationUserDetailsGetter.loadUserByUsername, username pedido:"+username);
    	User user=null;
    	try
    	{
    		user = userRepository.findByLogin(username);
    	}
    	catch(NoResultException e)
    	{
    		throw new UsernameNotFoundException("User with login " + username + "  has not been found.");
    	}
        return new AuthenticationUserDetails(user);
    }
}