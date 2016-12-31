package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.EvaluacionDAO;
import gamification.model.Evaluacion;

@Repository
public class EvaluacionDAOImpl implements EvaluacionDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Evaluacion> listarEvaluaciones() 
	{
		return (List<Evaluacion>) sessionFactory.getCurrentSession().createQuery("from Evaluacion").getResultList();
	}

	@Override
	@Transactional
	public void agregarEvaluacion(Evaluacion evaluacion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(evaluacion);
	}

	@Override
	public Evaluacion getById(Integer evaluacionId) 
	{
		return (Evaluacion) sessionFactory.getCurrentSession().createQuery("from Evaluacion where id="+evaluacionId).getSingleResult();
	}

	@Override
	public void grabarEvaluacion(Evaluacion evaluacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(evaluacion);
	}

}
