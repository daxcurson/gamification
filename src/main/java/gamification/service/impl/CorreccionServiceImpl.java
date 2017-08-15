package gamification.service.impl;

import java.util.Date;
//import java.util.Iterator;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CorreccionDAO;
import gamification.dao.CorreccionPreguntaDAO;
import gamification.model.Capacitador;
import gamification.model.Configuracion;
import gamification.model.Correccion;
import gamification.model.CorreccionPregunta;
//import gamification.model.CorreccionPregunta;
import gamification.model.EvaluacionTomada;
import gamification.model.Inscripcion;
import gamification.service.ConfiguracionService;
import gamification.service.CorreccionService;
import gamification.service.InscripcionService;

@Service
public class CorreccionServiceImpl implements CorreccionService 
{
	//private static Logger log=LogManager.getLogger(CorreccionServiceImpl.class);
	@Autowired
	private CorreccionDAO correccionDAO;
	@Autowired
	private ConfiguracionService configuracionService;
	@Autowired
	private InscripcionService inscripcionService;
	@Autowired CorreccionPreguntaDAO correccionPreguntaDAO;
	@Override
	public Correccion getCorreccionById(int id) 
	{
		return correccionDAO.getById(id);
	}
	@Override
	@Transactional
	public void grabarCorreccion(EvaluacionTomada evaluacion_tomada, Correccion correccion,Capacitador capacitador) 
	{
		correccion.setEvaluacion_tomada(evaluacion_tomada);
		correccion.setFecha(new Date());
		correccion.setCapacitador(capacitador);
		// Ahora: si la correccion es mayor a la nota de aprobacion, vamos
		// a dar por aprobado. Hay que grabar ese dato en la inscripcion
		// de la persona en este curso.
		Configuracion nota=configuracionService.buscarConfiguracionNombre("nota_aprobacion");
		if(nota!=null)
		{
			int nota_aprobacion=Integer.parseInt(nota.getConfig_valor());
			if(correccion.getNota()>=nota_aprobacion)
			{
				// Busco la inscripcion del estudiante para aprobarla.
				Inscripcion insc=evaluacion_tomada.getInscripcion();
				insc.setAprobada(true);
				inscripcionService.grabarInscripcion(insc);
			}
		}
		correccionDAO.save(correccion);
	}
	@Override
	public CorreccionPregunta obtenerCorreccionPregunta(int respuestaId)
	{
		return correccionPreguntaDAO.getByRespuesta(respuestaId);
	}
}
