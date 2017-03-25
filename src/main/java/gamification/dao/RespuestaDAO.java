package gamification.dao;

import java.util.List;

import gamification.model.EvaluacionTomada;
import gamification.model.Respuesta;

public interface RespuestaDAO 
{
	public Respuesta getById(int id);
	public void agregarRespuesta(Respuesta r);
	public void grabarRespuesta(Respuesta r);
	public List<Respuesta> listarRespuestas(EvaluacionTomada evaluacion);
}
