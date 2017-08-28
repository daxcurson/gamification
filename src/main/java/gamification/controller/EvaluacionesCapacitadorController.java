package gamification.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.Capacitador;
import gamification.model.Correccion;
import gamification.model.CorreccionPregunta;
import gamification.model.EvaluacionTomada;
import gamification.model.Nota;
import gamification.model.Respuesta;
import gamification.service.CorreccionService;
//import gamification.service.CursoService;
import gamification.service.EvaluacionService;
import gamification.service.impl.AuthenticationUserDetails;

@Controller
@RequestMapping(value="evaluaciones_capacitador")
@SessionAttributes({"correccion","evaluacion_tomada","correccion_pregunta","respuesta"})
@DescripcionClase("Capacitador: Corregir Evaluaciones")
public class EvaluacionesCapacitadorController extends AppController
{
	private static Logger log=LogManager.getLogger(EvaluacionesCapacitadorController.class);
	
	//@Autowired
	//private CursoService cursoService;
	@Autowired
	private EvaluacionService evaluacionService;
	@Autowired
	private CorreccionService correccionService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value="/listar/{curso_id}")
	@Descripcion(value="Capacitador: Mostrar lista de evaluaciones a corregir para un curso",permission="ROLE_EVALUACIONES_CAPACITADOR_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_MOSTRAR_MENU')")
	public ModelAndView listarEvaluacionesCorregir(@PathVariable("curso_id") int curso_id)
	{
		log.trace("Listo evaluaciones a corregir");
		ModelAndView modelo=new ModelAndView("evaluaciones_capacitador_index");
		modelo.addObject("evaluaciones_corregir",evaluacionService.listarEvaluacionesCorregir(curso_id));
		return modelo;
	}
	private ModelAndView cargarExamen(String vista,EvaluacionTomada examen,Correccion correccion)
	{
		ModelAndView v=new ModelAndView(vista);
		v.addObject("evaluacion_tomada",examen);
		v.addObject("curso",examen.getCurso_oferta().getCurso());
		v.addObject("estudiante",examen.getInscripcion().getEstudiante());
		v.addObject("correccion",correccion);
		return v;
	}
	@RequestMapping(value="/nueva_correccion/{evaluacion_tomada_id}",method=RequestMethod.GET)
	@Descripcion(value="Capacitador: corregir examen",permission="ROLE_EVALUACIONES_CAPACITADOR_CORREGIR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView nuevaCorreccion(@PathVariable("evaluacion_tomada_id") int evaluacion_tomada_id)
	{
		Correccion c=new Correccion();
		EvaluacionTomada examen=evaluacionService.getEvaluacionTomadaById(evaluacion_tomada_id);
		c.setEvaluacion_tomada(examen);
		log.trace("Estoy en nuevaCorreccion, evaluacion_tomada.id vale "+examen.getId());
		ModelAndView modelo=this.cargarExamen("evaluaciones_capacitador_corregir",examen,c);
		return modelo;
	}

	@RequestMapping(value="/nueva_correccion/{evaluacion_tomada_id}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView grabarNuevaCorreccion(
			@Valid @ModelAttribute("correccion") Correccion correccion,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarExamen("evaluaciones_capacitador_corregir",correccion.getEvaluacion_tomada(),correccion);
			return modelo;
		}
		else
		{
			AuthenticationUserDetails user= (AuthenticationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Capacitador c=(Capacitador) user.getUser().getPersona();
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones_capacitador/listar/"+correccion.getEvaluacion_tomada().getCurso_oferta().getCurso().getId());
			correccionService.grabarCorreccion(correccion,c);
			redirectAttributes.addFlashAttribute("message","Correccion Agregada");
			return modelo;
		}
	}
	@RequestMapping(value="/editar_correccion/{correccion_id}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView editarCorreccion(@PathVariable("correccion_id") int correccion_id)
	{
		Correccion correccion=correccionService.getCorreccionById(correccion_id);
		EvaluacionTomada examen=evaluacionService.getEvaluacionTomadaById(correccion.getEvaluacion_tomada().getId());
		log.trace("Estoy en editarCorreccion, evaluacion_tomada.id vale "+examen.getId());
		ModelAndView modelo=this.cargarExamen("evaluaciones_capacitador_corregir",examen,correccion);
		return modelo;
	}
	@RequestMapping(value="/editar_correccion/{correccion_id}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView grabarEdicionCorreccion(
			@Valid @ModelAttribute("correccion") Correccion correccion,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarExamen("evaluaciones_capacitador_corregir",correccion.getEvaluacion_tomada(),correccion);
			return modelo;
		}
		else
		{
			AuthenticationUserDetails user= (AuthenticationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Capacitador c=(Capacitador) user.getUser().getPersona();
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones_capacitador/listar/"+correccion.getEvaluacion_tomada().getCurso_oferta().getCurso().getId());
			correccionService.grabarCorreccion(correccion,c);
			redirectAttributes.addFlashAttribute("message","Correccion Editada");
			return modelo;
		}
	}
	
	private ModelAndView cargarFormMostrarRespuesta(int respuesta_id,
			EvaluacionTomada evaluacion_tomada,
			Correccion correccion,
			CorreccionPregunta correccionPregunta)
	{
		ModelAndView modelo=new ModelAndView("evaluaciones_capacitador_mostrar_pregunta");
		modelo.addObject("evaluacion_tomada",evaluacion_tomada);
		log.trace("Estoy en cargarFormMostrarRespuesta, evaluacion_tomada.id vale: "+evaluacion_tomada.getId());

		Optional<Respuesta> r=evaluacion_tomada.getRespuestas().stream().filter(resp -> resp.getId() == respuesta_id).findFirst();
		modelo.addObject("respuesta",r.get());
		modelo.addObject("notas",Nota.values());
		modelo.addObject("correccion",correccion);
		modelo.addObject("correccion_pregunta",correccionPregunta);
		return modelo;
	}
	@RequestMapping(value="/mostrar_respuesta/{respuesta_id}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView mostrarRespuesta(@PathVariable("respuesta_id") int respuesta_id,
			@ModelAttribute("evaluacion_tomada") EvaluacionTomada evaluacion_tomada,
			@ModelAttribute("correccion") Correccion correccion
			)
	{
		log.trace("Estoy en mostrarRespuesta, evaluacion_tomada.id vale: "+evaluacion_tomada.getId());
		return this.cargarFormMostrarRespuesta(respuesta_id, evaluacion_tomada,correccion, new CorreccionPregunta());
	}
	@RequestMapping(value="/mostrar_respuesta/{respuesta_id}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView grabarComentarioProfesor(@PathVariable("respuesta_id") int respuesta_id,
			@ModelAttribute("evaluacion_tomada") EvaluacionTomada evaluacion_tomada,
			@ModelAttribute("correccion_pregunta") CorreccionPregunta correccion_pregunta,
			@ModelAttribute("correccion") Correccion correccion,
			@ModelAttribute("respuesta") Respuesta respuesta,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		log.trace("Estoy en grabarComentarioProfesor, evaluacion_tomada.id vale: "+evaluacion_tomada.getId());
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormMostrarRespuesta(respuesta_id, evaluacion_tomada,correccion, correccion_pregunta);
			return modelo;
		}
		else
		{
			correccion_pregunta.setCorreccion(correccion);
			correccion_pregunta.setRespuesta(respuesta);
			log.trace("La respuesta es: "+respuesta.getId()+": "+respuesta.getValor_respuesta());
			if(correccion.getCorrecciones()==null)
				correccion.setCorrecciones(new LinkedList<CorreccionPregunta>());
			correccion.getCorrecciones().add(correccion_pregunta);
			log.trace("Ahora hay:");
			Iterator<CorreccionPregunta> i=correccion.getCorrecciones().iterator();
			while(i.hasNext())
			{
				CorreccionPregunta p=i.next();
				log.trace("Correccion pregunta: "+p.getComentarios());
			}
			AuthenticationUserDetails user= (AuthenticationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Capacitador c=(Capacitador) user.getUser().getPersona();
			log.trace("El id de la correccion es: "+correccion.getId());
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones_capacitador/editar_correccion/"+correccion.getId());
			correccionService.grabarCorreccion(correccion,c);
			redirectAttributes.addFlashAttribute("message","Comentario agregado");
			return modelo;
		}
	}
}
