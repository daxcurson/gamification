package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CursoDAO;
import gamification.dao.CursoOfertaDAO;
import gamification.dao.TemarioDAO;
import gamification.exceptions.CursoExistenteException;
import gamification.model.Capacitador;
import gamification.model.Curso;
import gamification.model.CursoOferta;
import gamification.model.Estudiante;
import gamification.model.TemaCurso;
import gamification.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService 
{
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private TemarioDAO temarioDAO;
	@Autowired
	private CursoOfertaDAO cursoOfertaDAO;
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

	@Override
	public List<CursoOferta> listarOfertas(Integer cursoId) 
	{
		return cursoOfertaDAO.listarOfertas(cursoId);
	}

	@Override
	public void agregarCursoOferta(CursoOferta oferta, int cursoId) 
	{
		Curso c=cursoDAO.getCursoById(cursoId);
		oferta.setCurso(c);
		cursoOfertaDAO.agregarOferta(oferta);
	}

	@Override
	public CursoOferta getCursoOfertaById(Integer ofertaId) 
	{
		return cursoOfertaDAO.getById(ofertaId);
	}

	@Override
	@Transactional
	public void grabarCursoOferta(CursoOferta oferta) 
	{
		cursoOfertaDAO.grabarOferta(oferta);
	}

	@Override
	public List<CursoOferta> listarOfertasTodas() 
	{
		return cursoOfertaDAO.listarOfertasTodas();
	}

	@Override
	public List<Curso> listarCursosCapacitador(Capacitador c) 
	{
		return cursoDAO.listarCursosCapacitador(c.getId());
	}

	@Override
	public List<Curso> listarCursosCursados(Estudiante e) 
	{
		return cursoDAO.listarCursosCursados(e.getId());
	}
}
