package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CapacitadorDAO;
import gamification.model.Capacitador;

@Repository
public class CapacitadorDAOImpl implements CapacitadorDAO
{
	private static Logger log=LogManager.getLogger(CapacitadorDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Capacitador getById(int id) 
	{
		log.trace("Estoy en CapacitadorDAOImpl.getById, id pedido:"+id);
		return (Capacitador) sessionFactory.getCurrentSession().createQuery("from Capacitador where id="+id).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Capacitador> listarCapacitadores() 
	{
		log.trace("Estoy en CapacitadorDAOImpl.listarCapacitadores");
		return (List<Capacitador>) sessionFactory.getCurrentSession().createQuery("from Capacitador").getResultList();
	}

	@Override
	@Transactional
	public void agregar(Capacitador capacitador) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(capacitador);
	}

	@Override
	public void grabar(Capacitador capacitador) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(capacitador);
	}
}
