package gamification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamification.dao.ConfiguracionDAO;
import gamification.model.Configuracion;
import gamification.service.ConfiguracionService;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService 
{
	@Autowired
	private ConfiguracionDAO configuracionDAO;
	@Override
	public Configuracion buscarConfiguracionNombre(String nombre) 
	{
		return configuracionDAO.getByNombre(nombre);
	}
}
