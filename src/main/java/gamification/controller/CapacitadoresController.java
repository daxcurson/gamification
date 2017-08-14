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
import gamification.exceptions.CapacitadorExistenteException;
import gamification.model.Capacitador;
import gamification.model.Group;
import gamification.model.propertyeditor.GroupEditor;
import gamification.service.CapacitadorService;
import gamification.service.GroupService;
import gamification.service.impl.AuthenticationUserDetails;

@Controller
@RequestMapping(value="capacitadores")
@SessionAttributes("capacitador")
@DescripcionClase("Capacitadores")
public class CapacitadoresController extends AppController
{
	private static Logger log=LogManager.getLogger(EstudiantesController.class);

	@Autowired
	private CapacitadorService capacitadorService;
	@Autowired
	private GroupService groupService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(Group.class, new GroupEditor(groupService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de capacitadores y menu",permission="ROLE_CAPACITADORES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("capacitadores_index");
		// Leemos los capacitadores que hay.
		modelo.addObject("capacitadores",capacitadorService.listarCapacitadores());
		return modelo;
	}
	
	private ModelAndView cargarFormCapacitador(String vista,Capacitador capacitador)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("capacitador",capacitador);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarCapacitador(Model model)
	{
		ModelAndView modelo=this.cargarFormCapacitador("capacitadores_add",new Capacitador());
		return modelo;
	}
	@Descripcion(value="Agregar Capacitador",permission="ROLE_CAPACITADORES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarCapacitador(@Valid @ModelAttribute("capacitador")
	Capacitador capacitador,
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
			ModelAndView modelo=this.cargarFormCapacitador("capacitadores_add",capacitador);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/capacitadores/index");
			try
			{
				capacitadorService.agregarCapacitador(capacitador);
				redirectAttributes.addFlashAttribute("message","Capacitador agregado exitosamente");
			}
			catch(CapacitadorExistenteException e)
			{
				model.addAttribute("message","Ese nombre de capacitador ya existe, por favor elija otro");
				modelo=this.cargarFormCapacitador("capacitadores_add",capacitador);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_EDIT')")
	@RequestMapping(value="/edit/{capacitadorId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("capacitadorId") Integer capacitadorId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		Capacitador capacitador=capacitadorService.getCapacitadorById(capacitadorId);
		ModelAndView modelo=this.cargarFormCapacitador("capacitadores_edit",capacitador);
		return modelo;
	}
	@Descripcion(value="Editar Capacitador",permission="ROLE_CAPACITADORES_EDIT")
	@RequestMapping(value="/edit/{capacitadorId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_EDIT')")
	public ModelAndView editarCapacitador(@PathVariable("capacitadorId") Integer capacitadorId,
			@Valid @ModelAttribute("capacitador") Capacitador capacitador,
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
			ModelAndView modelo=this.cargarFormCapacitador("capacitadores_edit",capacitador);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/capacitadores/index");
			try
			{
				capacitadorService.grabarCapacitador(capacitador);
				redirectAttributes.addFlashAttribute("message","Capacitador editado exitosamente");
			}
			catch(CapacitadorExistenteException e)
			{
				model.addAttribute("message","Ese nombre de capacitador ya existe, por favor elija otro");
				modelo=this.cargarFormCapacitador("capacitadores_edit",capacitador);
			}
			return modelo;
		}
	}
	/**
	 * Muestra el perfil del capacitador
	 * @param capacitador_id
	 * @return
	 */
	@Descripcion(value="Capacitadores: mostrar perfil",permission="ROLE_CAPACITADORES_MI_PERFIL")
	@RequestMapping(value="/mi_perfil/{capacitador_id}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CAPACITADORES_MI_PERFIL')")
	public ModelAndView mostrarPerfilCapacitador(@PathVariable("capacitador_id") int capacitador_id)
	{
		ModelAndView m=new ModelAndView("capacitadores_mi_perfil");
		// Finalmente es un riesgo de seguridad permitir que el id de la persona
		// sea leido de la variable de GET. La voy a ignorar, buscando la persona
		// que corresponde al usuario autenticado.
		AuthenticationUserDetails user=(AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Capacitador c=(Capacitador)user.getUser().getPersona();
		m.addObject("capacitador",c);
		return m;
	}
}
