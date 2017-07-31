package gamification.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="estudiantes")
public class Estudiante extends Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 592800616514661877L;
	@OneToMany(mappedBy="estudiante")
	private List<Inscripcion> inscripciones;
	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

}
