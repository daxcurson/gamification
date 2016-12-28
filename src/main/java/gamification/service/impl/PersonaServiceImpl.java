package gamification.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.PersonaDAO;
import gamification.dao.UserDAO;
import gamification.exceptions.PersonaExistenteException;
import gamification.model.Persona;
import gamification.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService
{
	@Autowired
	private PersonaDAO personaDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public Persona getPersonaById(int id) 
	{
		return personaDAO.getById(id);
	}

	@Override
	public List<Persona> listarPersonas() 
	{
		return personaDAO.listarPersonas();
	}

	@Override
	public void agregarPersona(Persona p) throws PersonaExistenteException 
	{
		try
		{
			// Si es usuario del sistema, hay que grabar un usuario.
			if(p.getUsuario_sistema())
				userDAO.save(p.getUser());
			personaDAO.agregar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new PersonaExistenteException();
        }
	}

	@Override
	public void grabarPersona(Persona p) throws PersonaExistenteException 
	{
		try
		{
			personaDAO.grabar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new PersonaExistenteException();
        }
	}

}
