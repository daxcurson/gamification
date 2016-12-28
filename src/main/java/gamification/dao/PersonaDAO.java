package gamification.dao;

import java.util.List;

import gamification.model.Persona;

public interface PersonaDAO 
{
	public Persona getById(int id);
	public List<Persona> listarPersonas();
	void agregar(Persona persona);
	void grabar(Persona persona);
}
