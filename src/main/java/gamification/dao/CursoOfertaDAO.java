package gamification.dao;

import java.util.List;

import gamification.model.CursoOferta;

public interface CursoOfertaDAO 
{
	List<CursoOferta> listarOfertas(Integer cursoId);
	void agregarOferta(CursoOferta oferta);
	CursoOferta getById(Integer ofertaId);
	void grabarOferta(CursoOferta oferta);
}
