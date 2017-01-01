package gamification.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.Evaluacion;
import gamification.service.EvaluacionService;

@Controller
@RequestMapping("evaluaciones")
@SessionAttributes("evaluacion")
@DescripcionClase("Evaluaciones")
public class EvaluacionesController extends AppController
{
	private static Logger log=LogManager.getLogger(EvaluacionesController.class);
	@Autowired
	private EvaluacionService evaluacionService;
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de evaluaciones y menu",permission="ROLE_EVALUACIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("evaluaciones_index");
		modelo.addObject("evaluaciones",evaluacionService.listarEvaluaciones());
		return modelo;
	}
	private ModelAndView cargarFormEvaluacion(String vista,Evaluacion evaluacion)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("evaluacion",evaluacion);
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarEvaluacion(Model model)
	{
		ModelAndView modelo=this.cargarFormEvaluacion("evaluaciones_add",new Evaluacion());
		return modelo;
	}
	@Descripcion(value="Agregar Evaluacion",permission="ROLE_EVALUACIONES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarEvaluacion(@Valid @ModelAttribute("evaluacion")
	Evaluacion evaluacion,
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
			ModelAndView modelo=this.cargarFormEvaluacion("evaluaciones_add",evaluacion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones/index");
			evaluacionService.agregarEvaluacion(evaluacion);
			redirectAttributes.addFlashAttribute("message","Evaluacion agregada exitosamente");
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_EDIT')")
	@RequestMapping(value="/edit/{evaluacionId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditarEvaluacion(@PathVariable("evaluacionId") Integer evaluacionId,
			Model model)
	{
		// Busco el curso y lo cargo en el formulario.
		Evaluacion evaluacion=evaluacionService.getEvaluacionById(evaluacionId);
		ModelAndView modelo=this.cargarFormEvaluacion("evaluaciones_edit",evaluacion);
		return modelo;
	}
	@Descripcion(value="Editar Evaluacion",permission="ROLE_EVALUACIONES_EDIT")
	@RequestMapping(value="/edit/{evaluacionId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_EDIT')")
	public ModelAndView editarEvaluacion(@PathVariable("evaluacionId") Integer evaluacionId,
			@Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
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
			ModelAndView modelo=this.cargarFormEvaluacion("evaluaciones_edit",evaluacion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones/index");
			evaluacionService.grabarEvaluacion(evaluacion);
			redirectAttributes.addFlashAttribute("message","Evaluacion editada exitosamente");
			return modelo;
		}
	}
}