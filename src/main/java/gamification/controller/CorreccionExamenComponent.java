package gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gamification.model.Correccion;
import gamification.model.EvaluacionTomada;
import gamification.service.CorreccionService;
import gamification.service.EvaluacionService;

@Component
public class CorreccionExamenComponent 
{
	@Autowired
	private EvaluacionService evaluacionService;
	@Autowired
	private CorreccionService correccionService;
	
	public EvaluacionTomada getEvaluacionTomada(int evaluacionTomadaId)
	{
		return evaluacionService.getEvaluacionTomadaById(evaluacionTomadaId);
	}
	
	public void save(Correccion correccion)
	{
		correccionService.save(correccion);
	}
}
