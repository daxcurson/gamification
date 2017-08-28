package gamification.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

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
	
	/**
	 * Puntos obtenidos por el estudiante. Calculado mediante una formula usando
	 * la base de datos. Es el total de los puntos obtenidos por las inscripciones
	 * aprobadas.
	 */
	@Formula("(select sum(insc.puntos) from inscripciones insc where insc.aprobada=1)")
	private int puntos;
	
	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
