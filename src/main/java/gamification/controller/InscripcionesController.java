package gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.User;
import gamification.service.InscripcionService;

@Controller
@RequestMapping("inscripciones")
@SessionAttributes("inscripcion")
@DescripcionClase("Inscripciones")
public class InscripcionesController 
{
	@Autowired
	private InscripcionService inscripcionService;
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de inscripciones a cursos",permission="ROLE_INSCRIPCIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_INSCRIPCIONES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("inscripciones_index");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelo.addObject("inscripciones",inscripcionService.listarInscripcionesEstudiante(user.getId()));
		return modelo;
	}
}
