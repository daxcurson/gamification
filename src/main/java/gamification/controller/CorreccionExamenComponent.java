package gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gamification.model.Correccion;
import gamification.model.Curso;
import gamification.model.Estudiante;
import gamification.model.EvaluacionTomada;
import gamification.service.CorreccionService;
import gamification.service.CursoService;
import gamification.service.EstudianteService;
import gamification.service.EvaluacionService;

@Component
public class CorreccionExamenComponent 
{
	@Autowired
	private EvaluacionService evaluacionService;
	@Autowired
	private CorreccionService correccionService;
	@Autowired
	private CursoService cursoService;
	@Autowired 
	private EstudianteService estudianteService;
	
	public EvaluacionTomada getEvaluacionTomada(int evaluacionTomadaId)
	{
		return evaluacionService.getEvaluacionTomadaById(evaluacionTomadaId);
	}
	public Estudiante getEstudianteById(int estudianteId)
	{
		return estudianteService.getEstudianteById(estudianteId);
	}
	public Curso getCursoById(int cursoId)
	{
		return cursoService.getCursoById(cursoId);
	}
	public void save(Correccion correccion)
	{
		correccionService.save(correccion);
	}
}
