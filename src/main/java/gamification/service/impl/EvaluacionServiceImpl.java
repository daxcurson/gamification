package gamification.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.EvaluacionDAO;
import gamification.dao.EvaluacionTomadaDAO;
import gamification.model.Evaluacion;
import gamification.model.EvaluacionTomada;
import gamification.model.Respuesta;
import gamification.service.EvaluacionService;

@Service
public class EvaluacionServiceImpl implements EvaluacionService 
{
	private static Logger log=LogManager.getLogger(EvaluacionServiceImpl.class);
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
	@Transactional
	public void tomarEvaluacion(EvaluacionTomada evaluacion) 
	{
		// La fecha de la evaluacion tiene que estar cargada!!!
		evaluacion.setFecha_evaluacion(new Date());
		// Me voy a asegurar que todas las preguntas de la evaluacion
		// tengan configurado el link hacia la EvaluacionTomada.
		List<Respuesta> lista=evaluacion.getRespuestas();
		for(Respuesta r:lista)
		{
			r.setEvaluacion_tomada(evaluacion);
			log.trace("La respuesta tiene una pregunta??? Seria esta: "+r.getPregunta());
		}
		evaluacionTomadaDAO.agregar(evaluacion);
	}

	@Override
	public List<EvaluacionTomada> listarEvaluacionesCorregir(int curso_id) 
	{
		return evaluacionTomadaDAO.listarEvaluacionesCorregir(curso_id);
	}

	@Override
	public EvaluacionTomada getEvaluacionTomadaById(int evaluacion_tomada_id) 
	{
		return evaluacionTomadaDAO.getById(evaluacion_tomada_id);
	}
}
