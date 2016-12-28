package gamification.service;

import java.util.List;

import gamification.exceptions.PersonaExistenteException;
import gamification.model.Persona;

public interface PersonaService 
{
	public Persona getPersonaById(int id);
	public List<Persona> listarPersonas();
	public void agregarPersona(Persona p) throws PersonaExistenteException;
	public void grabarPersona(Persona p) throws PersonaExistenteException;
}
