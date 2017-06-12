package gamification.dao;

import java.util.List;

import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;

public interface CursoDAO 
{
	List<Curso> listarCursos();
	Curso getCursoById(Integer cursoId);
	void agregarCurso(Curso curso) throws CursoExistenteException;
	void grabarCurso(Curso curso) throws CursoExistenteException;
	List<Curso> listarCursosCapacitador(Integer capacitadorId);
}
