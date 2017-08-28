package gamification.service;

import gamification.model.Capacitador;
import gamification.model.Correccion;
import gamification.model.CorreccionPregunta;

public interface CorreccionService 
{
	Correccion getCorreccionById(int id);
	void grabarCorreccion(Correccion correccion, Capacitador capacitador);
	CorreccionPregunta obtenerCorreccionPregunta(int respuestaId);
}
