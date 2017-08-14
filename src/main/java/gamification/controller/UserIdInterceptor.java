package gamification.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import gamification.service.UserDetails;

/**
 * Intercepta las llamadas a los metodos de los Controllers para inyectar
 * en ellos el user id para mostrar en el View. Es brillante ;-).
 * @author daxcurson
 *
 */
public class UserIdInterceptor extends HandlerInterceptorAdapter
{
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView)
			throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && auth.getPrincipal()!=null &&
				!(auth.getPrincipal() instanceof String))
		{
			UserDetails u=(UserDetails) auth.getPrincipal();
			modelAndView.addObject("persona",u.getUser().getPersona());
		}
	}
}
