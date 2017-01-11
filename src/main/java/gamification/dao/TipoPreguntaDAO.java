package gamification.dao;

import java.util.List;

import gamification.model.TipoPregunta;

public interface TipoPreguntaDAO 
{

	public List<TipoPregunta> listarTiposPregunta();
	public TipoPregunta getById(int id);
}
