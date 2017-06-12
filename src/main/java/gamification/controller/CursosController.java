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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.exceptions.CursoExistenteException;
import gamification.model.Capacitador;
import gamification.model.Curso;
import gamification.model.CursoOferta;
import gamification.model.TemaCurso;
import gamification.model.propertyeditor.CapacitadorEditor;
import gamification.model.propertyeditor.CursoEditor;
import gamification.service.CapacitadorService;
import gamification.service.CursoService;
import gamification.service.impl.AuthenticationUserDetails;

@Controller
@RequestMapping(value="cursos")
@SessionAttributes({"curso","oferta"})
@DescripcionClase("Cursos")
public class CursosController extends AppController
{
	private static Logger log=LogManager.getLogger(CursosController.class);
	@Autowired
	private CursoService cursoService;
	@Autowired
	private CapacitadorService capacitadorService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Curso.class, new CursoEditor(cursoService));
		binder.registerCustomEditor(Capacitador.class, new CapacitadorEditor(capacitadorService));
	}
	
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de cursos y menu",permission="ROLE_CURSOS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("cursos_index");
		// Leemos los usuarios que hay.
		modelo.addObject("cursos",cursoService.listarCursos());
		return modelo;
	}
	private ModelAndView cargarFormCurso(String vista,Curso curso)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("curso",curso);
		// Vamos a leer la lista de capacitadores.
		modelo.addObject("capacitadores",capacitadorService.listarCapacitadores());
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarCurso(Model model)
	{
		ModelAndView modelo=this.cargarFormCurso("cursos_add",new Curso());
		return modelo;
	}
	@Descripcion(value="Agregar Curso",permission="ROLE_CURSOS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarCurso(@Valid @ModelAttribute("curso")
	Curso curso,
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
			ModelAndView modelo=this.cargarFormCurso("cursos_add",curso);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/index");
			try
			{
				cursoService.agregarCurso(curso);
				redirectAttributes.addFlashAttribute("message","Curso agregado exitosamente");
			}
			catch(CursoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de curso ya existe, por favor elija otro");
				modelo=this.cargarFormCurso("cursos_add",curso);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_EDIT')")
	@RequestMapping(value="/edit/{cursoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("cursoId") Integer cursoId,
			Model model)
	{
		// Busco el curso y lo cargo en el formulario.
		Curso curso=cursoService.getCursoById(cursoId);
		ModelAndView modelo=this.cargarFormCurso("cursos_edit",curso);
		return modelo;
	}
	@Descripcion(value="Editar Curso",permission="ROLE_CURSOS_EDIT")
	@RequestMapping(value="/edit/{cursoId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_EDIT')")
	public ModelAndView editarCurso(@PathVariable("cursoId") Integer cursoId,
			@Valid @ModelAttribute("curso") Curso curso,
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
			ModelAndView modelo=this.cargarFormCurso("cursos_edit",curso);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/index");
			try
			{
				cursoService.grabarCurso(curso);
				redirectAttributes.addFlashAttribute("message","Curso editado exitosamente");
			}
			catch(CursoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de curso ya existe, por favor elija otro");
				modelo=this.cargarFormCurso("cursos_edit",curso);
			}
			return modelo;
		}
	}
	@Descripcion(value="Listar temario del curso",permission="ROLE_CURSOS_TEMARIO_LISTAR")
	@RequestMapping(value="/temario/{cursoId}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_TEMARIO_LISTAR')")
	public ModelAndView listarTemario(@PathVariable("cursoId") Integer cursoId)
	{
		ModelAndView m=new ModelAndView("cursos_temario_index");
		Curso curso=cursoService.getCursoById(cursoId);
		List<TemaCurso> temas=cursoService.listarTemas(cursoId);
		m.addObject("curso",curso);
		m.addObject("temario",temas);
		return m;
	}
	private ModelAndView cargarFormTema(String vista,TemaCurso tema)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("tema",tema);
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_TEMA_AGREGAR')")
	@RequestMapping(value="/temario_add/{cursoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarTema(Model model,
			@PathVariable("cursoId") Integer cursoId
			)
	{
		TemaCurso t=new TemaCurso();
		ModelAndView modelo=this.cargarFormTema("cursos_temario_add",t);
		return modelo;
	}
	@Descripcion(value="Agregar Tema de Curso",permission="ROLE_CURSOS_TEMA_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_TEMA_AGREGAR')")
	@RequestMapping(value = "/temario_add/{cursoId}", method = RequestMethod.POST)
	public ModelAndView agregarTemaCurso(@Valid @ModelAttribute("tema")
	@PathVariable("cursoId") Integer cursoId,
	TemaCurso tema,
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
			ModelAndView modelo=this.cargarFormTema("cursos_temario_add",tema);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/temario/"+cursoId);
			cursoService.agregarTemaCurso(tema,cursoId);
			redirectAttributes.addFlashAttribute("message","Tema agregado exitosamente");
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_TEMA_EDIT')")
	@RequestMapping(value="/temario_edit/{temaId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditarTema(@PathVariable("temaId") Integer temaId,
			Model model)
	{
		// Busco el curso y lo cargo en el formulario.
		TemaCurso tema=cursoService.getTemaById(temaId);
		ModelAndView modelo=this.cargarFormTema("cursos_temario_edit",tema);
		return modelo;
	}
	@Descripcion(value="Editar Tema de curso",permission="ROLE_CURSOS_TEMA_EDIT")
	@RequestMapping(value="/temario_edit/{temaId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_TEMA_EDIT')")
	public ModelAndView editarTema(@PathVariable("temaId") Integer temaId,
			@Valid @ModelAttribute("tema") TemaCurso tema,
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
			ModelAndView modelo=this.cargarFormTema("cursos_temario_edit",tema);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/temario/"+tema.getCurso().getId());
			cursoService.grabarTemaCurso(tema);
			redirectAttributes.addFlashAttribute("message","Tema editado exitosamente");
			return modelo;
		}
	}

	@Descripcion(value="Listar ofertas del curso",permission="ROLE_CURSOS_OFERTAS_LISTAR")
	@RequestMapping(value="/ofertas/{cursoId}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_OFERTAS_LISTAR')")
	public ModelAndView listarOfertasCurso(
			@PathVariable("cursoId") Integer cursoId
			)
	{
		ModelAndView modelo=new ModelAndView("cursos_ofertas_index");
		// Leemos los usuarios que hay.
		modelo.addObject("curso",cursoService.getCursoById(cursoId));
		modelo.addObject("ofertas",cursoService.listarOfertas(cursoId));
		return modelo;
	}
	private ModelAndView cargarFormCursoOferta(String vista,CursoOferta oferta)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("oferta",oferta);
		modelo.addObject("cursos_lista",cursoService.listarCursos());
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_OFERTAS_AGREGAR')")
	@RequestMapping(value="/oferta_add/{cursoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarOfertaCurso(Model model,
			@PathVariable("cursoId") Integer cursoId
			)
	{
		ModelAndView modelo=this.cargarFormCursoOferta("cursos_ofertas_add",new CursoOferta());
		return modelo;
	}
	@Descripcion(value="Agregar Oferta de Curso",permission="ROLE_CURSOS_OFERTAS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_OFERTAS_AGREGAR')")
	@RequestMapping(value = "/oferta_add/{cursoId}", method = RequestMethod.POST)
	public ModelAndView agregarCursoOferta(@Valid @ModelAttribute("oferta")
	CursoOferta oferta,
	BindingResult result,ModelMap model,
	@PathVariable("cursoId") Integer cursoId,
	final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormCursoOferta("cursos_ofertas_add",oferta);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/ofertas/"+cursoId);
			cursoService.agregarCursoOferta(oferta,cursoId);
			redirectAttributes.addFlashAttribute("message","Oferta de Curso agregada exitosamente");
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_OFERTAS_EDIT')")
	@RequestMapping(value="/oferta_edit/{ofertaId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditarOferta(@PathVariable("ofertaId") Integer ofertaId,
			Model model)
	{
		// Busco el curso y lo cargo en el formulario.
		CursoOferta oferta=cursoService.getCursoOfertaById(ofertaId);
		ModelAndView modelo=this.cargarFormCursoOferta("cursos_ofertas_edit",oferta);
		return modelo;
	}
	@Descripcion(value="Editar Oferta de Curso",permission="ROLE_CURSOS_OFERTAS_EDIT")
	@RequestMapping(value="/oferta_edit/{ofertaId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CURSOS_OFERTAS_EDIT')")
	public ModelAndView editarCursoOferta(@PathVariable("ofertaId") Integer ofertaId,
			@Valid @ModelAttribute("oferta") CursoOferta oferta,
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
			ModelAndView modelo=this.cargarFormCursoOferta("cursos_ofertas_edit",oferta);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/cursos/ofertas/"+oferta.getCurso().getId());
			cursoService.grabarCursoOferta(oferta);
			redirectAttributes.addFlashAttribute("message","Oferta de Curso editada exitosamente");
			return modelo;
		}
	}
	@Descripcion(value="Capacitador: lista de mis cursos",permission="ROLE_MIS_CURSOS")
	@RequestMapping(value="/mis_cursos")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MIS_CURSOS')")
	public ModelAndView listarMisCursos()
	{
		ModelAndView modelo=new ModelAndView("cursos_mis_cursos");
		AuthenticationUserDetails user=(AuthenticationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Capacitador c=(Capacitador) user.getUser().getPersona();
		modelo.addObject("mis_cursos",cursoService.listarCursosCapacitador(c));
		return modelo;
	}
}
