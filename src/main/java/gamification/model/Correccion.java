package gamification.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="correcciones")
public class Correccion implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2306110333162451710L;
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
	@OneToMany(cascade={CascadeType.ALL},targetEntity=CorreccionPregunta.class,mappedBy="correccion")
	private List<CorreccionPregunta> correcciones;
	private Date fecha;
	private int nota;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonBackReference
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
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public List<CorreccionPregunta> getCorrecciones() {
		return correcciones;
	}
	public void setCorrecciones(List<CorreccionPregunta> correcciones) {
		this.correcciones = correcciones;
	}
}
