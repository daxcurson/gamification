package gamification.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="estudiantes")
public class Estudiante extends Persona
{
	
	@NotEmpty
	private String nombre;
	
	/**
	 * Valores por defecto de persona. No es usuario del sistema y esta habilitada.
	 */
	public Estudiante()
	{
		this.usuario_sistema=false;
		this.setHabilitada(true);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
