package gamification.service.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamification.dao.CapacitadorDAO;
import gamification.dao.UserDAO;
import gamification.exceptions.CapacitadorExistenteException;
import gamification.model.Capacitador;
import gamification.model.User;
import gamification.service.CapacitadorService;

@Service
public class CapacitadorServiceImpl implements CapacitadorService 
{

	@Autowired
	private CapacitadorDAO capacitadorDAO;
	@Autowired
	private UserDAO userDAO;
	@Override
	public List<Capacitador> listarCapacitadores() 
	{
		return capacitadorDAO.listarCapacitadores();
	}

	@Override
	public void agregarCapacitador(Capacitador capacitador) throws CapacitadorExistenteException 
	{
		try
		{
			capacitador.setUsuario_sistema(true);
			User user=capacitador.getUser();
			user.setEnabled(1);
			// Hay que encriptar el password antes de grabarlo!!!
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());

			userDAO.save(capacitador.getUser());
			capacitadorDAO.agregar(capacitador);
		}
		catch(ConstraintViolationException e)
		{
			throw new CapacitadorExistenteException();
		}
	}

	@Override
	public Capacitador getCapacitadorById(Integer capacitadorId) 
	{
		return capacitadorDAO.getById(capacitadorId);
	}

	@Override
	public void grabarCapacitador(Capacitador capacitador) throws CapacitadorExistenteException 
	{
		capacitadorDAO.grabar(capacitador);
	}
}
