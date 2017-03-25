package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.PreguntaDAO;
import gamification.dao.TipoPreguntaDAO;
import gamification.model.Evaluacion;
import gamification.model.Pregunta;
import gamification.model.TipoPregunta;
import gamification.service.PreguntaService;

@Service
public class PreguntaServiceImpl implements PreguntaService 
{
	@Autowired
	private TipoPreguntaDAO tipoPreguntaDAO;
	@Autowired
	private PreguntaDAO preguntaDAO;
	
	@Override
	public Pregunta getById(int id)
	{
		return preguntaDAO.getById(id);
	}
	@Override
	public List<TipoPregunta> listarTiposPreguntas() 
	{
		return tipoPreguntaDAO.listarTiposPregunta();
	}
	@Override
	public TipoPregunta getTipoPreguntaById(int id) 
	{
		return tipoPreguntaDAO.getById(id);
	}
	@Override
	public void agregarPregunta(Pregunta p,Evaluacion e)
	{
		p.setEvaluacion(e);
		preguntaDAO.agregarPregunta(p);
	}
}
