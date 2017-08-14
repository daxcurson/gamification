package gamification.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.ConfiguracionDAO;
import gamification.model.Configuracion;

@Repository
public class ConfiguracionDAOImpl implements ConfiguracionDAO
{

	private static Logger log=LogManager.getLogger(ConfiguracionDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Configuracion getById(int id) 
	{
		log.trace("Estoy en ConfiguracionDAOImpl.getById, id pedido:"+id);
		return (Configuracion) sessionFactory.getCurrentSession().createQuery("from Configuracion where id="+id).getSingleResult();
	}

	@Override
	public Configuracion getByNombre(String nombre) {
		String query="from Configuracion where config_nombre=:nombre";
		Query q=sessionFactory.getCurrentSession().createQuery(query);
		q.setParameter("nombre", nombre);
		return (Configuracion) q.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracion> listarConfiguraciones() 
	{
		log.trace("Estoy en CapacitadorDAOImpl.listarConfiguraciones");
		return (List<Configuracion>) sessionFactory.getCurrentSession().createQuery("from Configuracion").getResultList();
	}

	@Override
	@Transactional
	public void agregar(Configuracion c) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(c);
	}

	@Override
	public void grabar(Configuracion c) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(c);
	}

}
