package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.EvaluacionDAO;
import gamification.dao.EvaluacionTomadaDAO;
import gamification.model.Evaluacion;
import gamification.model.EvaluacionTomada;
import gamification.service.EvaluacionService;

@Service
public class EvaluacionServiceImpl implements EvaluacionService 
{
	@Autowired
	private EvaluacionDAO evaluacionDAO;
	@Autowired
	private EvaluacionTomadaDAO evaluacionTomadaDAO;
	@Override
	public List<Evaluacion> listarEvaluaciones() 
	{
		return evaluacionDAO.listarEvaluaciones();
	}

	@Override
	public void agregarEvaluacion(Evaluacion evaluacion) 
	{
		evaluacionDAO.agregarEvaluacion(evaluacion);
	}

	@Override
	public Evaluacion getEvaluacionById(Integer evaluacionId) 
	{
		return evaluacionDAO.getById(evaluacionId);
	}

	@Override
	public void grabarEvaluacion(Evaluacion evaluacion) 
	{
		evaluacionDAO.grabarEvaluacion(evaluacion);
	}

	@Override
	public List<EvaluacionTomada> listarEvaluacionesTomadas(int estudiante_id) 
	{
		return evaluacionTomadaDAO.listarEvaluacionesTomadas(estudiante_id);
	}

	@Override
	public void tomarEvaluacion(EvaluacionTomada evaluacion) 
	{
	}

	@Override
	public void guardarOrdenMagnet(EvaluacionTomada evaluacion) 
	{
	}
}
