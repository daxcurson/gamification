package gamification.service;

import gamification.exceptions.GrupoExistenteException;
import gamification.exceptions.UsuarioExistenteException;
import gamification.model.User;

public interface InstalacionService 
{
	public void grabarUsuarioAdministrador(User user) throws UsuarioExistenteException, GrupoExistenteException;
}
