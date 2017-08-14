package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.CursoDAO;
import gamification.exceptions.CursoExistenteException;
import gamification.model.Curso;

@Repository
public class CursoDAOImpl implements CursoDAO 
{
	private static Logger log=LogManager.getLogger(CursoDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursos() 
	{
		log.trace("Estoy en CursoDAOImpl.listarEstudiante");
		return (List<Curso>) sessionFactory.getCurrentSession().createQuery("from Curso").getResultList();
	}

	@Override
	public Curso getCursoById(Integer cursoId) 
	{
		log.trace("Estoy en CursoDAOImpl.getCursoById, id pedido:"+cursoId);
		return (Curso) sessionFactory.getCurrentSession().createQuery("from Curso where id="+cursoId).getSingleResult();
	}

	@Override
	@Transactional
	public void agregarCurso(Curso curso) throws CursoExistenteException 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(curso);
	}

	@Override
	@Transactional
	public void grabarCurso(Curso curso) throws CursoExistenteException 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(curso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursosCapacitador(Integer capacitadorId) 
	{
		return (List<Curso>) sessionFactory.getCurrentSession().createQuery("from Curso where capacitador_id="+capacitadorId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursosCursados(int id) 
	{
		return (List<Curso>) sessionFactory.getCurrentSession().createNativeQuery("select cursos.id,cursos.codigo_curso,cursos.nombre from cursos,curso_ofertas,inscripciones "
				+ "where cursos.id=curso_ofertas.curso_id and curso_ofertas.id=inscripciones.curso_oferta_id "
				+ "and inscripciones.estudiante_id="+id).getResultList();
	}

}
