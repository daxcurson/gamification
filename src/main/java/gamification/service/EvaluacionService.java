package gamification.service;

import java.util.List;

import gamification.model.Evaluacion;

public interface EvaluacionService 
{
	List<Evaluacion> listarEvaluaciones();
	void agregarEvaluacion(Evaluacion evaluacion);
	Evaluacion getEvaluacionById(Integer evaluacionId);
	void grabarEvaluacion(Evaluacion evaluacion);
}
