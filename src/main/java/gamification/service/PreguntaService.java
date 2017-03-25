package gamification.service;

import java.util.List;

import gamification.model.Evaluacion;
import gamification.model.Pregunta;
import gamification.model.TipoPregunta;

public interface PreguntaService 
{
	public Pregunta getById(int id);
	public List<TipoPregunta> listarTiposPreguntas();
	public TipoPregunta getTipoPreguntaById(int id);
	void agregarPregunta(Pregunta p, Evaluacion e);
}
