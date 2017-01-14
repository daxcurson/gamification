package gamification.service;

import java.util.List;

import gamification.model.Inscripcion;

public interface InscripcionService 
{
	public Inscripcion getInscripcionById(int id);
	public List<Inscripcion> listarInscripcionesEstudiante(int estudiante_id);
}
