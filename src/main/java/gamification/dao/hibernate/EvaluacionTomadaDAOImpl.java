package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gamification.dao.EvaluacionTomadaDAO;
import gamification.model.EvaluacionTomada;

@Repository
public class EvaluacionTomadaDAOImpl implements EvaluacionTomadaDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EvaluacionTomada getById(int id) 
	{
		return (EvaluacionTomada)sessionFactory.getCurrentSession().createQuery("from EvaluacionTomada where id="+id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluacionTomada> listarEvaluacionesTomadas(int estudiante_id) 
	{
		return (List<EvaluacionTomada>)sessionFactory.getCurrentSession().createQuery("from EvaluacionTomada where estudiante.id="+estudiante_id).getResultList();
	}

}
