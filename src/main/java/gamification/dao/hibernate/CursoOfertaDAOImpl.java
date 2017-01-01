package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CursoOfertaDAO;
import gamification.model.CursoOferta;

public class CursoOfertaDAOImpl implements CursoOfertaDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CursoOferta> listarOfertas(Integer cursoId) 
	{
		return (List<CursoOferta>) sessionFactory.getCurrentSession().createQuery("from CursoOferta where curso_id="+cursoId).getResultList();
	}

	@Override
	@Transactional
	public void agregarOferta(CursoOferta oferta) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(oferta);
	}

	@Override
	public CursoOferta getById(Integer ofertaId) 
	{
		return (CursoOferta) sessionFactory.getCurrentSession().createQuery("from CursoOferta where id="+ofertaId).getSingleResult();
	}

	@Override
	public void grabarOferta(CursoOferta oferta) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(oferta);
	}

}
