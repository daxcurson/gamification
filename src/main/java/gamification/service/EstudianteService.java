package gamification.service;

import java.util.List;

import gamification.exceptions.EstudianteExistenteException;
import gamification.model.Estudiante;

public interface EstudianteService 
{
	public Estudiante getEstudianteById(int id);
	public List<Estudiante> listarEstudiantes();
	public void agregarEstudiante(Estudiante p) throws EstudianteExistenteException;
	public void grabarEstudiante(Estudiante p) throws EstudianteExistenteException;
}
