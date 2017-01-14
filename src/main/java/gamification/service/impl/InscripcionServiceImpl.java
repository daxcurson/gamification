package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.InscripcionDAO;
import gamification.model.Inscripcion;
import gamification.service.InscripcionService;

@Service
public class InscripcionServiceImpl implements InscripcionService 
{
	@Autowired
	private InscripcionDAO inscripcionDAO;
	@Override
	public Inscripcion getInscripcionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscripcion> listarInscripcionesEstudiante(int estudiante_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
