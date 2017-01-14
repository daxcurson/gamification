package gamification.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import gamification.model.Inscripcion;
import gamification.service.InscripcionService;
import gamification.service.impl.AuthenticationUserDetails;

@Controller
@RequestMapping("inscripciones")
@SessionAttributes("inscripcion")
@DescripcionClase("Inscripciones")
public class InscripcionesController 
{
	private static Logger log=LogManager.getLogger(InscripcionesController.class);

	@Autowired
	private InscripcionService inscripcionService;
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de inscripciones a cursos",permission="ROLE_INSCRIPCIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("inscripciones_index");
		AuthenticationUserDetails user= (AuthenticationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelo.addObject("inscripciones",inscripcionService.listarInscripcionesEstudiante(user.getId()));
		return modelo;
	}
	public ModelAndView cargarFormInscripcion(String vista,Inscripcion inscripcion)
	{
		ModelAndView form=new ModelAndView(vista);
		return form;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_ADD')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarEvaluacion(Model model)
	{
		ModelAndView modelo=this.cargarFormInscripcion("inscripciones_add",new Inscripcion());
		return modelo;
	}
	@Descripcion(value="Agregar Evaluacion",permission="ROLE_INSCRIPCIONES_ADD")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_ADD')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarInscripcion(@Valid @ModelAttribute("inscripcion")
	Inscripcion inscripcion,
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
			ModelAndView modelo=this.cargarFormInscripcion("inscripciones_add",inscripcion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/evaluaciones/index");
			inscripcionService.agregarInscripcion(inscripcion);
			redirectAttributes.addFlashAttribute("message","Evaluacion agregada exitosamente");
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_EDIT')")
	@RequestMapping(value="/edit/{inscripcionId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditarInscripcion(@PathVariable("inscripcionId") Integer inscripcionId,
			Model model)
	{
		// Busco el curso y lo cargo en el formulario.
		Inscripcion inscripcion=inscripcionService.getInscripcionById(inscripcionId);
		ModelAndView modelo=this.cargarFormInscripcion("inscripciones_edit",inscripcion);
		return modelo;
	}
	@Descripcion(value="Editar Inscripcion",permission="ROLE_INSCRIPCIONES_EDIT")
	@RequestMapping(value="/edit/{inscripcionId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_EDIT')")
	public ModelAndView editarEvaluacion(@PathVariable("inscripcionId") Integer inscripcionId,
			@Valid @ModelAttribute("inscripcion") Inscripcion inscripcion,
			BindingResult result,Model model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormInscripcion("inscripciones_edit",inscripcion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/inscripciones/index");
			inscripcionService.grabarInscripcion(inscripcion);
			redirectAttributes.addFlashAttribute("message","Inscripcion editada exitosamente");
			return modelo;
		}
	}
}
