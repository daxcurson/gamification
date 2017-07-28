package gamification.model;

import javax.persistence.*;

@Entity
@Table(name="estudiantes")
public class Estudiante extends Persona
{
	/**
	 * Valores por defecto de persona. No es usuario del sistema y esta habilitada.
	 */
	public Estudiante()
	{
		this.usuario_sistema=false;
		this.setHabilitada(true);
	}
	
}
