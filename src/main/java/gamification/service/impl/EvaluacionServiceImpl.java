package gamification.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CorreccionDAO;
import gamification.dao.EvaluacionDAO;
import gamification.dao.EvaluacionTomadaDAO;
import gamification.model.Correccion;
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
	@Autowired
	private CorreccionDAO correccionDAO;
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
		Iterator<Respuesta> iterator=lista.iterator();
		while(iterator.hasNext())
		{
			Respuesta r=iterator.next();
			r.setEvaluacion_tomada(evaluacion);
			log.trace("La respuesta tiene una pregunta??? Seria esta: "+r.getPregunta());
			if(r.getPregunta()!=null)
			{
				log.trace("El texto de la pregunta es : "+r.getPregunta().getTexto_pregunta()+" y su id es "+r.getPregunta().getId());
			}
			else
			{
				// Los videos de youtube no tienen una pregunta. Viene una respuesta
				// para una pregunta nula. Voy a eliminar esa respuesta.
				log.trace("Respuesta con pregunta nula. Remuevo la respuesta. "+r.getId());
				iterator.remove();
			}
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

	@Override
	public void grabarCorreccion(EvaluacionTomada evaluacion_tomada, Correccion correccion) 
	{
		correccion.setEvaluacion_tomada(evaluacion_tomada);
		correccion.setFecha(new Date());
		correccionDAO.save(correccion);
	}
}
