package gamification.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="estudiantes")
public class Estudiante extends Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 592800616514661877L;

	/**
	 * Valores por defecto de persona. No es usuario del sistema y esta habilitada.
	 */
	public Estudiante()
	{
		this.usuario_sistema=false;
		this.setHabilitada(true);
	}
	
}
