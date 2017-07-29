package gamification.service;

import gamification.model.Correccion;

public interface CorreccionService 
{
	Correccion getCorreccionById(int id);
	void save(Correccion correccion);
}
