package gamification.controller;

import gamification.documentation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="configuracion")
@DescripcionClase("Configuracion del sistema")
public class ConfiguracionController extends AppController 
{

	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU'))")
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar opciones configurables",permission="ROLE_CONFIGURACION_MOSTRAR_MENU")
	public String menuConfiguracion()
	{
		return "configuracion_index";
	}
}
