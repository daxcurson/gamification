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
	public void grabarCorreccion(Correccion correccion,Capacitador capacitador) 
	{
		correccion.setFecha(new Date());
		correccion.setCapacitador(capacitador);
		// Ahora: si la correccion es mayor a la nota de aprobacion, vamos
		// a dar por aprobado. Hay que grabar ese dato en la inscripcion
		// de la persona en este curso.
		Configuracion nota=configuracionService.buscarConfiguracionNombre("nota_aprobacion");
		if(nota!=null)
		{
			int nota_aprobacion=Integer.parseInt(nota.getConfig_valor());
			Inscripcion insc=correccion.getEvaluacion_tomada().getInscripcion();
			boolean aprobada_antes=insc.isAprobada();
			if(correccion.getNota()>=nota_aprobacion)
			{
				// Busco la inscripcion del estudiante para aprobarla.
				// Si no esta aprobada, la apruebo y le agrego los puntos del curso. 
				// Si antes estaba aprobada, y ahora no lo esta, le quito los puntos.
				if(!aprobada_antes)
				{
					insc.setAprobada(true);
					insc.setPuntos(correccion.getEvaluacion_tomada().getCurso_oferta().getCurso().getPuntos());
				}
			}
			else
			{
				if(aprobada_antes)
				{
					insc.setAprobada(false);
					insc.setPuntos(0);
				}
			}
			inscripcionService.grabarInscripcion(insc);
		}
		correccionDAO.save(correccion);
	}
	@Override
	public CorreccionPregunta obtenerCorreccionPregunta(int respuestaId)
	{
		return correccionPreguntaDAO.getByRespuesta(respuestaId);
	}
}
