package gamification.service;

import java.util.List;

import gamification.exceptions.CapacitadorExistenteException;
import gamification.model.Capacitador;

public interface CapacitadorService 
{
	public List<Capacitador> listarCapacitadores();
	public void agregarCapacitador(Capacitador capacitador) throws CapacitadorExistenteException;
	public Capacitador getCapacitadorById(Integer capacitadorId);
	public void grabarCapacitador(Capacitador capacitador) throws CapacitadorExistenteException;
}
