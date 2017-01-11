package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gamification.dao.TipoPreguntaDAO;
import gamification.model.TipoPregunta;

@Repository
public class TipoPreguntaDAOImpl implements TipoPreguntaDAO 
{
	private static Logger log=LogManager.getLogger(TipoPreguntaDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPregunta> listarTiposPregunta() 
	{
		log.trace("Estoy en listarTiposPregunta()");
		return (List<TipoPregunta>) sessionFactory.getCurrentSession().createQuery("from TipoPregunta").getResultList();
	}

	@Override
	public TipoPregunta getById(int id) 
	{
		return (TipoPregunta) sessionFactory.getCurrentSession().createQuery("from TipoPregunta where id="+id);
	}
}
