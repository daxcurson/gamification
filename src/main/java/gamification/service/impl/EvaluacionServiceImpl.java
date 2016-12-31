package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.EvaluacionDAO;
import gamification.model.Evaluacion;
import gamification.service.EvaluacionService;

@Service
public class EvaluacionServiceImpl implements EvaluacionService 
{
	@Autowired
	private EvaluacionDAO evaluacionDAO;
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
}
