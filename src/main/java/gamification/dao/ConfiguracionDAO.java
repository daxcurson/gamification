package gamification.dao;

import java.util.List;

import gamification.model.Configuracion;

public interface ConfiguracionDAO 
{
	Configuracion getById(int id);
	Configuracion getByNombre(String nombre);
	List<Configuracion> listarConfiguraciones();
	void agregar(Configuracion c);
	void grabar(Configuracion c);
}
