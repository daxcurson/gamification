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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.exceptions.GrupoExistenteException;
import gamification.model.Group;
import gamification.service.GroupService;

@Controller
@RequestMapping("groups")
@SessionAttributes("group")
@DescripcionClase("Grupos")
public class GroupsController extends AppController 
{
	private static Logger log=LogManager.getLogger(GroupsController.class);
	@Autowired
	private GroupService groupService;

	@RequestMapping({"/","/index"})
	@Descripcion(value="Listar grupos",permission="ROLE_GROUPS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_GROUPS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		return new ModelAndView("users_index");
	}
	private ModelAndView cargarFormGrupo(Group group)
	{
		ModelAndView modelo=new ModelAndView("groups_add");
		modelo.addObject("group",group);
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_GROUPS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregar(Model model)
	{
		ModelAndView modelo=this.cargarFormGrupo(new Group());
		return modelo;
	}
	@Descripcion(value="Agregar grupo",permission="ROLE_GROUPS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_GROUPS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("group")
	Group group,
	BindingResult result,ModelMap model, final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormGrupo(group);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("groups_index");
			try
			{
				groupService.save(group);
				redirectAttributes.addFlashAttribute("message","Grupo agregado exitosamente");
			}
			catch(GrupoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de grupo ya existe, por favor elija otro");
				modelo=this.cargarFormGrupo(group);
			}
			return modelo;
		}
	}
}
