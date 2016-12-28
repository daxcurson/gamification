package gamification.service;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import gamification.exceptions.UsuarioExistenteException;
import gamification.model.User;

public interface UserDetailsService {
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException, DataAccessException;
	public void save(User user) throws UsuarioExistenteException;
	public User getById(long id);
	public List<User> listUsers();
}