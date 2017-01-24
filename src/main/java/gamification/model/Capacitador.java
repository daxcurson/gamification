package gamification.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="capacitadores")
public class Capacitador extends Persona
{
	@NotEmpty
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
