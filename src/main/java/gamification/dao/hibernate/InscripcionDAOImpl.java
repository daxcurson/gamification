package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.InscripcionDAO;
import gamification.model.Inscripcion;

@Repository
public class InscripcionDAOImpl implements InscripcionDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Inscripcion getById(int id) 
	{
		return (Inscripcion)sessionFactory.getCurrentSession().createQuery("from Inscripcion where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inscripcion> listarInscripciones(int estudiante_id) 
	{
		return (List<Inscripcion>)sessionFactory.getCurrentSession().createQuery("from Inscripcion where estudiante_id="+estudiante_id).getResultList();
	}

	@Override
	@Transactional
	public void agregarInscripcion(Inscripcion inscripcion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(inscripcion);
	}

	@Override
	public void grabarInscripcion(Inscripcion inscripcion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(inscripcion);
	}
}
