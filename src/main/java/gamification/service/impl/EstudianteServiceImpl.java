package gamification.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.EstudianteDAO;
import gamification.dao.UserDAO;
import gamification.exceptions.EstudianteExistenteException;
import gamification.model.Estudiante;
import gamification.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService
{
	@Autowired
	private EstudianteDAO estudianteDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public Estudiante getEstudianteById(int id) 
	{
		return estudianteDAO.getById(id);
	}

	@Override
	public List<Estudiante> listarEstudiantes() 
	{
		return estudianteDAO.listarEstudiantes();
	}

	@Override
	public void agregarEstudiante(Estudiante p) throws EstudianteExistenteException 
	{
		try
		{
			// Si es usuario del sistema, hay que grabar un usuario.
			if(p.getUsuario_sistema())
				userDAO.save(p.getUser());
			estudianteDAO.agregar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new EstudianteExistenteException();
        }
	}

	@Override
	public void grabarEstudiante(Estudiante p) throws EstudianteExistenteException 
	{
		try
		{
			estudianteDAO.grabar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new EstudianteExistenteException();
        }
	}

}
