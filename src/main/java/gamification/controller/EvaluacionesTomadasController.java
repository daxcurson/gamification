package gamification.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.Evaluacion;
import gamification.model.EvaluacionTomada;
import gamification.model.User;
import gamification.service.EvaluacionService;

@Controller
@RequestMapping("evaluaciones_tomadas")
@SessionAttributes("evaluacion_tomada")
@DescripcionClase("Evaluaciones Tomadas")
public class EvaluacionesTomadasController 
{
	private static Logger log=LogManager.getLogger(EvaluacionesTomadasController.class);
	@Autowired
	private EvaluacionService evaluacionService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de evaluaciones tomadas y menu",permission="ROLE_EVALUACIONES_TOMADAS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_TOMADAS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("evaluaciones_tomadas_index");
		// Aqui pregunto al sistema cual es el usuario del sistema, y
		// pido las evaluaciones que este estudiante haya tomado.
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelo.addObject("evaluaciones",evaluacionService.listarEvaluacionesTomadas(user.getId()));
		return modelo;
	}
	/**
	 * Metodo interno para cargar el formulario de evaluacion. 
	 * @param vista Cual vista se desea, puede ser add, o edit, o la que sea.
	 * @param evaluacion el objeto evaluacion, que puede ser nuevo o leido de la base de datos.
	 * @return Devuelve el ModelAndView del examen.
	 */
	private ModelAndView cargarFormEvaluacion(String vista,EvaluacionTomada evaluacion)
	{
		ModelAndView m=new ModelAndView(vista);
		m.addObject("evaluacion_tomada",evaluacion);
		return m;
	}
	/**
	 * Muestra el formulario de una evaluacion que el estudiante puede tomar.
	 * @param evaluacionId Indica el id de la evaluacion que el estudiante desea tomar.
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_TOMADAS_ADD')")
	@RequestMapping(value="/{evaluacionId}/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormTomarEvaluacion(
			@PathVariable("evaluacionId") int evaluacionId
	)
	{
		// Hay que buscar los datos de la evaluacion, y asignarlo a la evaluacion
		// tomada.
		EvaluacionTomada t=new EvaluacionTomada();
		Evaluacion e=evaluacionService.getEvaluacionById(evaluacionId);
		t.setEvaluacion(e);
		return this.cargarFormEvaluacion("evaluaciones_tomadas_add", t);
	}
	@Descripcion(value="Dar evaluacion como estudiante",permission="ROLE_EVALUACIONES_TOMADAS_ADD")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_TOMADAS_ADD')")
	@RequestMapping(value = "/{evaluacionId}/add", method = RequestMethod.POST)
	public ModelAndView agregarEvaluacionTomada(@PathVariable("evaluacionId") int evaluacionId,
	@Valid @ModelAttribute("evaluacion_tomada")
	EvaluacionTomada evaluacion,
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
			ModelAndView modelo=this.cargarFormEvaluacion("evaluaciones_tomadas_add",evaluacion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones_tomadas/index");
			evaluacionService.tomarEvaluacion(evaluacion);
			redirectAttributes.addFlashAttribute("message","Evaluacion agregada exitosamente");
			return modelo;
		}
	}
	/**
	 * Recibe el orden de las filas de un Code Magnet. Lo guarda en la respuesta a la
	 * pregunta, porque se supone que esto se hace para cuando se entrega el examen.
	 * @param preguntaId id de la pregunta cuya respuesta se desea registrar.
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_TOMADAS_ADD')")
	@RequestMapping(value = "/{evaluacionId}/grabar_orden_pregunta/{preguntaId}", method = RequestMethod.POST)
	public @ResponseBody String grabarOrdenPreguntas(@PathVariable("preguntaId") int preguntaId,
			@ModelAttribute("evaluacion_tomada") EvaluacionTomada evaluacion
			)
	{
		return "success";
	}
}
