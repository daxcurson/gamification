package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.EstudianteDAO;
import gamification.model.Estudiante;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO
{
	private static Logger log=LogManager.getLogger(EstudianteDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Estudiante getById(int id) 
	{
		log.trace("Estoy en EstudianteDAOImpl.getById, id pedido:"+id);
		Session s=sessionFactory.openSession();
		Estudiante e=(Estudiante) s.createQuery("from Estudiante where id="+id).getSingleResult();
		s.close();
		//Estudiante e= (Estudiante) sessionFactory.getCurrentSession().createQuery("from Estudiante where id="+id).getSingleResult();
		log.trace("El nombre del estudiante es: "+e.getNombre());
		return e;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Estudiante> listarEstudiantes() 
	{
		log.trace("Estoy en EstudianteDAOImpl.listarEstudiante");
		return (List<Estudiante>) sessionFactory.getCurrentSession().createQuery("from Estudiante").getResultList();
	}

	@Override
	@Transactional
	public void agregar(Estudiante persona) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(persona);
	}

	@Override
	public void grabar(Estudiante persona) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(persona);
	}
}
