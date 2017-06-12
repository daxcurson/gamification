package gamification.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="capacitadores")
public class Capacitador extends Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2748449069620528033L;
	@NotEmpty
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
