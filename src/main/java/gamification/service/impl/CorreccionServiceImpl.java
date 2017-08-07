package gamification.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CorreccionDAO;
import gamification.model.Capacitador;
import gamification.model.Correccion;
import gamification.model.CorreccionPregunta;
import gamification.model.EvaluacionTomada;
import gamification.service.CorreccionService;

@Service
public class CorreccionServiceImpl implements CorreccionService 
{
	private static Logger log=LogManager.getLogger(CorreccionServiceImpl.class);
	@Autowired
	private CorreccionDAO correccionDAO;
	@Override
	public Correccion getCorreccionById(int id) 
	{
		return correccionDAO.getById(id);
	}
	@Override
	@Transactional
	public void grabarCorreccion(EvaluacionTomada evaluacion_tomada, Correccion correccion,Capacitador capacitador) 
	{
		correccion.setEvaluacion_tomada(evaluacion_tomada);
		correccion.setFecha(new Date());
		correccion.setCapacitador(capacitador);
		Iterator<CorreccionPregunta> i=correccion.getCorrecciones().iterator();
		while(i.hasNext())
		{
			CorreccionPregunta p=i.next();
			log.trace("Correccion pregunta: "+p.getComentarios());
		}
		correccionDAO.save(correccion);
	}
}
