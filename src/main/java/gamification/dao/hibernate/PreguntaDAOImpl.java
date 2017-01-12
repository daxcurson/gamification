package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.PreguntaDAO;
import gamification.model.Pregunta;

@Repository
public class PreguntaDAOImpl implements PreguntaDAO 
{
	private static Logger log=LogManager.getLogger(PreguntaDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Pregunta getById(int id) 
	{
		log.trace("Estoy en PreguntaDAOImpl.getById");
		return (Pregunta)sessionFactory.getCurrentSession().createQuery("from Pregunta where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> listarPreguntas() 
	{
		return (List<Pregunta>)sessionFactory.getCurrentSession().createQuery("from Pregunta").getResultList();
	}

	@Override
	@Transactional
	public void agregarPregunta(Pregunta p) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(p);
	}

	@Override
	public void grabarPregunta(Pregunta p) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(p);
	}
}
