package gamification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.TipoPreguntaDAO;
import gamification.model.TipoPregunta;
import gamification.service.PreguntaService;

@Service
public class PreguntaServiceImpl implements PreguntaService 
{
	@Autowired
	private TipoPreguntaDAO tipoPreguntaDAO;
	@Override
	public List<TipoPregunta> listarTiposPreguntas() 
	{
		return tipoPreguntaDAO.listarTiposPregunta();
	}
}
