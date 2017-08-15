package gamification.dao;

import gamification.model.CorreccionPregunta;

public interface CorreccionPreguntaDAO 
{
	public CorreccionPregunta getById(int id);
	public CorreccionPregunta getByRespuesta(int respuestaId);
}
