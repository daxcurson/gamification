package gamification.dao;

import java.util.List;

import gamification.model.Evaluacion;

public interface EvaluacionDAO 
{
	List<Evaluacion> listarEvaluaciones();
	void agregarEvaluacion(Evaluacion evaluacion);
	Evaluacion getById(Integer evaluacionId);
	void grabarEvaluacion(Evaluacion evaluacion);
}
