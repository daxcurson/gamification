package gamification.dao;

import java.util.List;

import gamification.model.Estudiante;

public interface EstudianteDAO 
{
	public Estudiante getById(int id);
	public List<Estudiante> listarEstudiantes();
	void agregar(Estudiante persona);
	void grabar(Estudiante persona);
}
