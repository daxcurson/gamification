package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.RespuestaDAO;
import gamification.model.EvaluacionTomada;
import gamification.model.Respuesta;

@Repository
public class RespuestaDAOImpl implements RespuestaDAO 
{
	private static Logger log=LogManager.getLogger(RespuestaDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Respuesta getById(int id) 
	{
		return (Respuesta)sessionFactory.getCurrentSession().createQuery("from Respuesta where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Respuesta> listarRespuestas(EvaluacionTomada evaluacion) 
	{
		return (List<Respuesta>)sessionFactory.getCurrentSession().createQuery("from Respuesta where evaluacion_tomada_id="+evaluacion.getId()).getResultList();
	}

	@Override
	@Transactional
	public void agregarRespuesta(Respuesta r) 
	{
		log.trace("Agrego respuesta!");
		sessionFactory.getCurrentSession().saveOrUpdate(r);
	}

	@Override
	public void grabarRespuesta(Respuesta r) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(r);
	}
}
