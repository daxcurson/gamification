package gamification.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Una respuesta a una pregunta formulada en una Evaluacion Tomada
 * @author daxcurson
 *
 */
@Entity
@Table(name="respuestas")
public class Respuesta implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6922287088060303731L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	@ManyToOne
	@JoinColumn(name="evaluacion_tomada_id")
	private EvaluacionTomada evaluacion_tomada;
	private String valor_respuesta;
	@OneToMany(targetEntity=CorreccionPregunta.class,mappedBy="respuesta")
	private List<CorreccionPregunta> correcciones;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public String getValor_respuesta() {
		return valor_respuesta;
	}
	public void setValor_respuesta(String valor_respuesta) {
		this.valor_respuesta = valor_respuesta;
	}
	public EvaluacionTomada getEvaluacion_tomada() {
		return evaluacion_tomada;
	}
	public void setEvaluacion_tomada(EvaluacionTomada evaluacion_tomada) {
		this.evaluacion_tomada = evaluacion_tomada;
	}
	public List<CorreccionPregunta> getCorrecciones() {
		return correcciones;
	}
	public void setCorrecciones(List<CorreccionPregunta> correcciones) {
		this.correcciones = correcciones;
	}
}
