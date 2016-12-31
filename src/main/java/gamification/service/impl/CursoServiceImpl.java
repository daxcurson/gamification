package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gamification.dao.CursoDAO;
import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;
import gamification.service.CursoService;

public class CursoServiceImpl implements CursoService 
{
	@Autowired
	private CursoDAO cursoDAO;
	@Override
	public List<Curso> listarCursos() 
	{
		return cursoDAO.listarCursos();
	}

	@Override
	public Curso getCursoById(Integer cursoId) 
	{
		return cursoDAO.getCursoById(cursoId);
	}

	@Override
	public void agregarCurso(Curso curso) throws CursoExistenteException 
	{
		cursoDAO.agregarCurso(curso);
	}

	@Override
	public void grabarCurso(Curso curso) throws CursoExistenteException 
	{
		cursoDAO.grabarCurso(curso);
	}

}
