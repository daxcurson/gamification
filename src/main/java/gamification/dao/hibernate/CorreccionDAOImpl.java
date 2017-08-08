package gamification.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CorreccionDAO;
import gamification.model.Correccion;

@Repository
public class CorreccionDAOImpl implements CorreccionDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Correccion getById(int correccionId) 
	{
		return (Correccion)sessionFactory.getCurrentSession().createQuery("from Correccion where id="+correccionId).getSingleResult();
	}

	@Override
	//@Transactional
	public void save(Correccion correccion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(correccion);
	}

}
