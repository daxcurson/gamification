package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import gamification.dao.InscripcionDAO;
import gamification.model.Estudiante;
import gamification.model.Inscripcion;
import gamification.service.InscripcionService;

@Service
public class InscripcionServiceImpl implements InscripcionService 
{
	@Autowired
	private InscripcionDAO inscripcionDAO;
	@Override
	public Inscripcion getInscripcionById(int id) 
	{
		return inscripcionDAO.getById(id);
	}

	@Override
	public List<Inscripcion> listarInscripcionesEstudiante(int estudiante_id) 
	{
		return inscripcionDAO.listarInscripciones(estudiante_id);
	}

	@Override
	public void grabarInscripcion(Inscripcion inscripcion) 
	{
		inscripcionDAO.grabarInscripcion(inscripcion);
	}

	@Override
	public void agregarInscripcion(Inscripcion inscripcion) 
	{
		// Me aseguro que la inscripcion este a nombre del usuario que esta
		// registrado en el sistema, sea capacitador o estudiante!
		AuthenticationUserDetails user=(AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		inscripcion.setEstudiante((Estudiante)user.getUser().getPersona());
		inscripcionDAO.agregarInscripcion(inscripcion);
	}
}
