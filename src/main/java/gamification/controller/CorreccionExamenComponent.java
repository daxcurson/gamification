package gamification.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gamification.model.Correccion;
import gamification.model.Curso;
import gamification.model.Estudiante;
import gamification.model.EvaluacionTomada;
import gamification.model.Inscripcion;
import gamification.model.Respuesta;
import gamification.service.CorreccionService;
import gamification.service.CursoService;
import gamification.service.EstudianteService;
import gamification.service.EvaluacionService;
import gamification.service.InscripcionService;

@Component
public class CorreccionExamenComponent 
{
	private static Logger log=LogManager.getLogger(CorreccionExamenComponent.class);
	@Autowired
	private EvaluacionService evaluacionService;
	@Autowired
	private CorreccionService correccionService;
	@Autowired
	private CursoService cursoService;
	@Autowired 
	private EstudianteService estudianteService;
	@Autowired
	private InscripcionService inscripcionService;
	
	public EvaluacionTomada getEvaluacionTomada(int evaluacionTomadaId)
	{
		EvaluacionTomada e= evaluacionService.getEvaluacionTomadaById(evaluacionTomadaId);
		List<Respuesta> r=e.getRespuestas();
		Hibernate.initialize(r);
		return e;
	}
	public Estudiante getEstudianteById(int estudianteId)
	{
		Estudiante e =estudianteService.getEstudianteById(estudianteId);
		log.trace("El nombre del estudiante es: "+e.getNombre());
		return e;
	}
	public Curso getCursoById(int cursoId)
	{
		return cursoService.getCursoById(cursoId);
	}
	public Inscripcion getInscripcionById(int inscripcionId)
	{
		return inscripcionService.getInscripcionById(inscripcionId);
	}
	public void save(Correccion correccion)
	{
		correccionService.save(correccion);
	}
}
