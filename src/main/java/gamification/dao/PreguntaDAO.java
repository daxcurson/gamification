package gamification.dao;

import java.util.List;

import gamification.model.Pregunta;

public interface PreguntaDAO 
{
	public Pregunta getById(int id);
	public List<Pregunta> listarPreguntas();
	public void agregarPregunta(Pregunta p);
	public void grabarPregunta(Pregunta p);
}
