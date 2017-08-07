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
	void tomarEvaluacion(EvaluacionTomada evaluacion);
	List<EvaluacionTomada> listarEvaluacionesCorregir(int curso_id);
	EvaluacionTomada getEvaluacionTomadaById(int evaluacion_tomada_id);
}
