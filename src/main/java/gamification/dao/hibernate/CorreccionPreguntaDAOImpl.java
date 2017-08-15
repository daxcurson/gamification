package gamification.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gamification.dao.CorreccionPreguntaDAO;
import gamification.model.CorreccionPregunta;

@Repository
public class CorreccionPreguntaDAOImpl implements CorreccionPreguntaDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CorreccionPregunta getById(int id) 
	{
		return (CorreccionPregunta)sessionFactory.getCurrentSession().createQuery("from CorreccionPregunta where id="+id).getSingleResult();
	}

	@Override
	public CorreccionPregunta getByRespuesta(int respuestaId) 
	{
		return (CorreccionPregunta)sessionFactory.getCurrentSession().createQuery("from CorreccionPregunta where pregunta.id="+respuestaId).getSingleResult();
	}

}
