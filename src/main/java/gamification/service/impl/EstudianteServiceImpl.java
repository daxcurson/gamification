package gamification.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamification.dao.EstudianteDAO;
import gamification.dao.UserDAO;
import gamification.exceptions.EstudianteExistenteException;
import gamification.model.Estudiante;
import gamification.model.User;
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
			// Todas las personas que se ingresaran van a ser usuarios del sistema.
			p.setUsuario_sistema(true);
			User user=p.getUser();
			user.setEnabled(1);
			// Hay que encriptar el password antes de grabarlo!!!
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());
			userDAO.save(user);
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
			User user=p.getUser();
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());
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
