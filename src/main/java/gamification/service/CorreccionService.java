package gamification.service;

import gamification.model.Capacitador;
import gamification.model.Correccion;
import gamification.model.EvaluacionTomada;

public interface CorreccionService 
{
	Correccion getCorreccionById(int id);
	void grabarCorreccion(EvaluacionTomada evaluacion_tomada, Correccion correccion, Capacitador capacitador);
}
