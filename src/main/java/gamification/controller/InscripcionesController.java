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
import org.springframework.ui.Model;
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
import gamification.model.CursoOferta;
import gamification.model.Inscripcion;
import gamification.model.propertyeditor.CursoOfertaEditor;
import gamification.service.CursoService;
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
	@Autowired
	private CursoService cursoService;

	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(CursoOferta.class, new CursoOfertaEditor(cursoService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de inscripciones a cursos",permission="ROLE_INSCRIPCIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("inscripciones_index");
		// Busco la persona (estudiante) con la que se relaciona el usuario.
		// Solo que el usuario esta dentro del AuthenticationUserDetails.
		// Por eso saco el AuthenticationUserDetails del contexto, de ahi saco
		// el usuario, y al usuario le pido la persona.
		// Podria sacar el "middle-man" haciendo que User implemente AuthenticationUserDetails.
		// Para pensar!
		AuthenticationUserDetails user= (AuthenticationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelo.addObject("inscripciones",inscripcionService.listarInscripcionesEstudiante(user.getUser().getPersona().getId()));
		return modelo;
	}
	public ModelAndView cargarFormInscripcion(String vista,Inscripcion inscripcion)
	{
		ModelAndView form=new ModelAndView(vista);
		form.addObject("inscripcion",inscripcion);
		form.addObject("cursos",cursoService.listarCursos());
		return form;
	}
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_INSCRIPCIONES_ADD') or hasRole('ROLE_INSCRIPCIONES_EDIT') )")
	@RequestMapping(value="/listar_ofertas/{cursoId}",method=RequestMethod.GET)
	public @ResponseBody List<CursoOferta> listarOfertas(@PathVariable("cursoId") int curso_id)
	{
		List<CursoOferta> listaOfertas=cursoService.listarOfertas(curso_id);
		return listaOfertas;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_ADD')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarInscripcion(Model model)
	{
		ModelAndView modelo=this.cargarFormInscripcion("inscripciones_add",new Inscripcion());
		return modelo;
	}
	@Descripcion(value="Inscribirse en curso",permission="ROLE_INSCRIPCIONES_ADD")
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
			ModelAndView modelo=new ModelAndView("redirect:/inscripciones/index");
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
