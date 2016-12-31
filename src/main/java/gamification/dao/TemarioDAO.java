package gamification.dao;

import java.util.List;

import gamification.model.TemaCurso;

public interface TemarioDAO 
{
	public List<TemaCurso> listarTemas(Integer cursoId);
	public TemaCurso getById(Integer temaId);
	public void agregarTema(TemaCurso tema);
	public void grabarTema(TemaCurso tema);
}
