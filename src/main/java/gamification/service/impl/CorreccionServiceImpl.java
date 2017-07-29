package gamification.service.impl;

import org.springframework.stereotype.Service;

import gamification.dao.CorreccionDAO;
import gamification.model.Correccion;
import gamification.service.CorreccionService;

@Service
public class CorreccionServiceImpl implements CorreccionService 
{
	private CorreccionDAO correccionDAO;
	@Override
	public Correccion getCorreccionById(int id) 
	{
		return correccionDAO.getById(id);
	}
	@Override
	public void save(Correccion correccion) 
	{
		correccionDAO.save(correccion);
	}

}
