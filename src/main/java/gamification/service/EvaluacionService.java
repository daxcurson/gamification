package gamification.service;

import java.util.List;

import gamification.model.Evaluacion;
import gamification.model.EvaluacionTomada;

public interface EvaluacionService 
{
	List<Evaluacion> listarEvaluaciones();
	void agregarEvaluacion(Evaluacion evaluacion);
	Evaluacion getEvaluacionById(Integer evaluacionId);
	void grabarEvaluacion(Evaluacion evaluacion);
	List<EvaluacionTomada> listarEvaluacionesTomadas(int estudiante_id);
}
