package gamification.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		return (EvaluacionTomada)sessionFactory.getCurrentSession().createQuery("from EvaluacionTomada where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluacionTomada> listarEvaluacionesTomadas(int estudiante_id) 
	{
		// Las evaluaciones tomadas por un estudiante estan relacionadas con la inscripcion,
		// no directamente con el estudiante. Uso un join para ir a buscar las
		// evaluaciones tomadas en todas las inscripciones.
		return (List<EvaluacionTomada>)sessionFactory.getCurrentSession().createQuery(
				"select eval from EvaluacionTomada eval,Inscripcion insc where "
				+ "eval.inscripcion.id=insc.id and insc.estudiante.id="+estudiante_id).getResultList();
	}

	@Override
	@Transactional
	public void agregar(EvaluacionTomada evaluacion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(evaluacion);
	}
	@Override
	public void grabar(EvaluacionTomada evaluacion)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(evaluacion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluacionTomada> listarEvaluacionesCorregir(int curso_id) {
		return (List<EvaluacionTomada>)sessionFactory.getCurrentSession().createQuery(
				"select eval from EvaluacionTomada eval,CursoOferta oferta,Curso curso where "
				+ "eval.curso_oferta.id=oferta.id "
				+ "and oferta.curso.id=curso.id").getResultList();
		}
}
