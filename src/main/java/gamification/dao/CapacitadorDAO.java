package gamification.dao;

import java.util.List;

import gamification.model.Capacitador;

public interface CapacitadorDAO 
{
	public void grabar(Capacitador capacitador);
	public void agregar(Capacitador capacitador);
	public List<Capacitador> listarCapacitadores();
	public Capacitador getById(int id);
}
