package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.CursoDAO;
import gamification.dao.TemarioDAO;
import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;
import gamification.model.TemaCurso;
import gamification.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService 
{
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private TemarioDAO temarioDAO;
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

	@Override
	public List<TemaCurso> listarTemas(Integer cursoId) 
	{
		return temarioDAO.listarTemas(cursoId);
	}

	@Override
	public void agregarTemaCurso(TemaCurso tema,int cursoId) 
	{
		tema.setCurso(cursoDAO.getCursoById(cursoId));
		temarioDAO.agregarTema(tema);
	}

	@Override
	public TemaCurso getTemaById(Integer temaId) 
	{
		return temarioDAO.getById(temaId);
	}

	@Override
	public void grabarTemaCurso(TemaCurso tema) 
	{
		temarioDAO.grabarTema(tema);
	}
}
