package gamification.dao;

import java.util.List;

import gamification.model.EvaluacionTomada;

public interface EvaluacionTomadaDAO 
{
	public EvaluacionTomada getById(int id);
	public List<EvaluacionTomada> listarEvaluacionesTomadas(int estudiante_id);
	public void agregar(EvaluacionTomada evaluacion);
	public void grabar(EvaluacionTomada evaluacion);
	public List<EvaluacionTomada> listarEvaluacionesCorregir(int curso_id);
}
