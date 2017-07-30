package gamification.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Calificacion de un examen tomado, realizada por un capacitador
 * @author daxcurson
 *
 */
@Entity
@Table(name="calificaciones")
public class Calificacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5188704115861563654L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="evaluacion_tomada_id")
	private EvaluacionTomada evaluacion_tomada;
	@ManyToOne
	@JoinColumn(name="capacitador_id")
	private Capacitador capacitador;
	private Date fecha;
	private double nota;
	private String comentarios;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EvaluacionTomada getEvaluacion_tomada() {
		return evaluacion_tomada;
	}
	public void setEvaluacion_tomada(EvaluacionTomada evaluacion_tomada) {
		this.evaluacion_tomada = evaluacion_tomada;
	}
	public Capacitador getCapacitador() {
		return capacitador;
	}
	public void setCapacitador(Capacitador capacitador) {
		this.capacitador = capacitador;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
