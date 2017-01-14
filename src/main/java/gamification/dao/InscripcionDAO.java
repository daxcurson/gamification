package gamification.dao;

import java.util.List;

import gamification.model.Inscripcion;

public interface InscripcionDAO 
{
	public Inscripcion getById(int id);
	public List<Inscripcion> listarInscripciones(int estudiante_id);
}
