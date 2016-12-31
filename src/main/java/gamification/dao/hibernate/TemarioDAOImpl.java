package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.TemarioDAO;
import gamification.model.TemaCurso;

@Repository
public class TemarioDAOImpl implements TemarioDAO 
{
	private static Logger log=LogManager.getLogger(TemarioDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<TemaCurso> listarTemas(Integer cursoId) 
	{
		return (List<TemaCurso>) sessionFactory.getCurrentSession().createQuery("from TemaCurso where curso_id="+cursoId).getResultList();
	}

	@Override
	public TemaCurso getById(Integer temaId) 
	{
		log.trace("Estoy en TemaCursoDAOImpl.getById, id pedido:"+temaId);
		return (TemaCurso) sessionFactory.getCurrentSession().createQuery("from TemaCurso where id="+temaId).getSingleResult();
	}

	@Override
	@Transactional
	public void agregarTema(TemaCurso tema) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(tema);
	}

	@Override
	public void grabarTema(TemaCurso tema)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(tema);
	}
}
