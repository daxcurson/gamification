package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CorreccionDAO;
import gamification.model.Correccion;
import gamification.model.CorreccionPregunta;

@Repository
public class CorreccionDAOImpl implements CorreccionDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Correccion getById(int correccionId) 
	{
		Correccion c= (Correccion)sessionFactory.getCurrentSession().createQuery("from Correccion where id="+correccionId).getSingleResult();
		List<CorreccionPregunta> cc=c.getCorrecciones();
		Hibernate.initialize(cc);
		return c;
	}

	@Override
	//@Transactional
	public void save(Correccion correccion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(correccion);
	}

}
