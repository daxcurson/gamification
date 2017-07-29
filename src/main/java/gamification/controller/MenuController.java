package gamification.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gamification.documentation.*;
import gamification.service.UserDetails;

@DescripcionClase(value="Menu")
@Controller
public class MenuController extends AppController
{
	@Descripcion(value="Mostrar menu principal",permission="ROLE_MENU_MOSTRAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MENU_MOSTRAR')")
	@RequestMapping({"/menu","/"})
	public ModelAndView menu(Model model)
	{
		// Le voy a preguntar al grupo cual es su vista principal para mostrar aqui.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails u=(UserDetails) auth.getPrincipal();
		ModelAndView modelo=new ModelAndView(u.getGroup().getVista_principal());
		return modelo;
	}

}
