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
import gamification.exceptions.EstudianteExistenteException;
import gamification.model.Group;
import gamification.model.Inscripcion;
import gamification.model.Estudiante;
import gamification.model.propertyeditor.GroupEditor;
import gamification.service.GroupService;
import gamification.service.InscripcionService;
import gamification.service.impl.AuthenticationUserDetails;
import gamification.service.CursoService;
import gamification.service.EstudianteService;

@Controller
@RequestMapping(value="estudiantes")
@SessionAttributes("estudiante")
@DescripcionClase("Estudiantes")
public class EstudiantesController extends AppController
{
	private static Logger log=LogManager.getLogger(EstudiantesController.class);

	@Autowired
	private EstudianteService estudianteService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private InscripcionService inscripcionService;
	@Autowired
	private GroupService groupService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(Group.class, new GroupEditor(groupService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de estudiantes y menu",permission="ROLE_ESTUDIANTES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("estudiantes_index");
		// Leemos los usuarios que hay.
		modelo.addObject("estudiantes",estudianteService.listarEstudiantes());
		return modelo;
	}

	private ModelAndView cargarFormEstudiante(String vista,Estudiante estudiante)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("estudiante",estudiante);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarEstudiante(Model model)
	{
		ModelAndView modelo=this.cargarFormEstudiante("estudiantes_add",new Estudiante());
		return modelo;
	}
	@Descripcion(value="Agregar Estudiante",permission="ROLE_ESTUDIANTES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarEstudiante(@Valid @ModelAttribute("estudiante")
	Estudiante estudiante,
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
			ModelAndView modelo=this.cargarFormEstudiante("estudiantes_add",estudiante);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/estudiantes/index");
			try
			{
				estudianteService.agregarEstudiante(estudiante);
				redirectAttributes.addFlashAttribute("message","Estudiante agregado exitosamente");
			}
			catch(EstudianteExistenteException e)
			{
				model.addAttribute("message","Ese nombre de estudiante ya existe, por favor elija otro");
				modelo=this.cargarFormEstudiante("estudiantes_add",estudiante);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_EDIT')")
	@RequestMapping(value="/edit/{estudianteId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("estudianteId") Integer estudianteId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		Estudiante estudiante=estudianteService.getEstudianteById(estudianteId);
		ModelAndView modelo=this.cargarFormEstudiante("estudiantes_edit",estudiante);
		return modelo;
	}
	@Descripcion(value="Editar Estudiante",permission="ROLE_ESTUDIANTES_EDIT")
	@RequestMapping(value="/edit/{estudianteId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_EDIT')")
	public ModelAndView editarEstudiante(@PathVariable("estudianteId") Integer estudianteId,
			@Valid @ModelAttribute("estudiante") Estudiante estudiante,
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
			ModelAndView modelo=this.cargarFormEstudiante("estudiantes_edit",estudiante);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/estudiantes/index");
			try
			{
				estudianteService.grabarEstudiante(estudiante);
				redirectAttributes.addFlashAttribute("message","Estudiante editado exitosamente");
			}
			catch(EstudianteExistenteException e)
			{
				model.addAttribute("message","Ese nombre de estudiante ya existe, por favor elija otro");
				model.addAttribute("type","danger");
				modelo=this.cargarFormEstudiante("estudiantes_edit",estudiante);
			}
			return modelo;
		}
	}
	@Descripcion(value="Mostrar perfil de Estudiante",permission="ROLE_ESTUDIANTES_MI_PERFIL")
	@RequestMapping(value="/mi_perfil/{estudianteId}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ESTUDIANTES_MI_PERFIL')")
	public ModelAndView mostrarPerfilEstudiante()
	{
		ModelAndView m=new ModelAndView("estudiantes_mi_perfil");
		// Aqui tienen:
		// mis cursos aprobados,
		// mis logros,
		// mis evaluaciones tomadas.
		AuthenticationUserDetails user=(AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Estudiante e=(Estudiante)user.getUser().getPersona();
		m.addObject("estudiante",e);
		List<Inscripcion> inscripciones=inscripcionService.listarInscripcionesEstudiante(e.getId());
		m.addObject("mis_inscripciones",inscripciones);
		return m;
	}
}
