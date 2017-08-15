package gamification.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;

import gamification.model.CorreccionPregunta;
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
	private CursoService cursoService;
	@Autowired 
	private EstudianteService estudianteService;
	@Autowired
	private InscripcionService inscripcionService;
	@Autowired
	private CorreccionService correccionService;
	
	public EvaluacionTomada getEvaluacionTomada(int evaluacionTomadaId)
	{
		EvaluacionTomada e= evaluacionService.getEvaluacionTomadaById(evaluacionTomadaId);
		List<Respuesta> r=e.getRespuestas();
		Hibernate.initialize(r);
		Iterator<Respuesta> i=r.iterator();
		while(i.hasNext())
		{
			Respuesta re=i.next();
			List<CorreccionPregunta> c=re.getCorrecciones();
			Hibernate.initialize(c);
		}
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
	public CorreccionPregunta getCorreccionPreguntaFor(int respuestaId)
	{
		// Si hay un nulo,
		CorreccionPregunta p=correccionService.obtenerCorreccionPregunta(respuestaId);
		if(p==null)
			p=new CorreccionPregunta();
		return p;
	}
	public Event buscarRespuesta(int respuestaId)
	{
		Map<String, Object> map = new HashMap<String,Object>();
	    map.put("respuesta", new Integer(respuestaId));
	    AttributeMap<Object> attributeMap = new LocalAttributeMap<Object>(map);
	    return new Event(this, "success", attributeMap);
	}
}
