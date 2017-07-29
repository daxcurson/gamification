package gamification.dao;

import gamification.model.Correccion;

public interface CorreccionDAO 
{
	Correccion getById(int correccionId);
	void save(Correccion correccion);
}
