package gamification.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.User;
import gamification.service.EvaluacionService;

@Controller
@RequestMapping("evaluaciones_tomadas")
@SessionAttributes("evaluacion")
@DescripcionClase("Evaluaciones Tomadas")
public class EvaluacionesTomadasController 
{
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
	public ModelAndView mostrarFormTomarEvaluacion()
	{
		return new ModelAndView("evaluaciones_tomadas_add");
	}
}
