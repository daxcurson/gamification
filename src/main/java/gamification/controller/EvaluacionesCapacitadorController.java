package gamification.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import gamification.documentation.Descripcion;
import gamification.documentation.DescripcionClase;
import gamification.model.EvaluacionTomada;
//import gamification.service.CursoService;
import gamification.service.EvaluacionService;

@Controller
@RequestMapping(value="evaluaciones_capacitador")
@SessionAttributes("evaluacion")
@DescripcionClase("Capacitador: Corregir Evaluaciones")
public class EvaluacionesCapacitadorController extends AppController
{
	private static Logger log=LogManager.getLogger(EvaluacionesCapacitadorController.class);
	
	//@Autowired
	//private CursoService cursoService;
	@Autowired
	private EvaluacionService evaluacionService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value="/listar/{curso_id}")
	@Descripcion(value="Capacitador: Mostrar lista de evaluaciones a corregir para un curso",permission="ROLE_EVALUACIONES_CAPACITADOR_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_MOSTRAR_MENU')")
	public ModelAndView listarEvaluacionesCorregir(@PathVariable("curso_id") int curso_id)
	{
		log.trace("Listo evaluaciones a corregir");
		ModelAndView modelo=new ModelAndView("evaluaciones_capacitador_index");
		modelo.addObject("evaluaciones_corregir",evaluacionService.listarEvaluacionesCorregir(curso_id));
		return modelo;
	}
	private ModelAndView cargarExamen(String vista,EvaluacionTomada examen)
	{
		ModelAndView v=new ModelAndView(vista);
		v.addObject("evaluacion_tomada",examen);
		v.addObject("curso",examen.getCurso_oferta().getCurso());
		v.addObject("estudiante",examen.getInscripcion().getEstudiante());
		return v;
	}
	@RequestMapping(value="/corregir/{evaluacion_tomada_id}")
	@Descripcion(value="Capacitador: corregir examen",permission="ROLE_EVALUACIONES_CAPACITADOR_CORREGIR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EVALUACIONES_CAPACITADOR_CORREGIR')")
	public ModelAndView mostrarExamenACorregir(@PathVariable("evaluacion_tomada_id") int evaluacion_tomada_id)
	{
		EvaluacionTomada examen=evaluacionService.getEvaluacionTomadaById(evaluacion_tomada_id);
		ModelAndView modelo=this.cargarExamen("evaluaciones_capacitador_corregir",examen);
		return modelo;
	}
}
