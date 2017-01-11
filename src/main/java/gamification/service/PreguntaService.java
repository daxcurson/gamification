package gamification.service;

import java.util.List;

import gamification.model.TipoPregunta;

public interface PreguntaService 
{
	public List<TipoPregunta> listarTiposPreguntas();
	public TipoPregunta getTipoPreguntaById(int id);
}
