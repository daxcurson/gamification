package gamification.service;

import java.util.List;

import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;
import gamification.model.CursoOferta;
import gamification.model.TemaCurso;

public interface CursoService 
{
	public List<Curso> listarCursos();
	public Curso getCursoById(Integer cursoId);
	public void agregarCurso(Curso curso) throws CursoExistenteException;
	public void grabarCurso(Curso curso) throws CursoExistenteException;
	public List<TemaCurso> listarTemas(Integer cursoId);
	public void agregarTemaCurso(TemaCurso tema, int cursoId);
	public TemaCurso getTemaById(Integer temaId);
	public void grabarTemaCurso(TemaCurso tema);
	public List<CursoOferta> listarOfertas(Integer cursoId);
	public void agregarCursoOferta(CursoOferta oferta, int cursoId);
	public CursoOferta getCursoOfertaById(Integer ofertaId);
	public void grabarCursoOferta(CursoOferta oferta);
}
