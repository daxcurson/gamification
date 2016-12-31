package gamification.service;

import java.util.List;

import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;

public interface CursoService 
{
	public List<Curso> listarCursos();
	public Curso getCursoById(Integer cursoId);
	public void agregarCurso(Curso curso) throws CursoExistenteException;
	public void grabarCurso(Curso curso) throws CursoExistenteException;
}
