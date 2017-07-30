package gamification.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Una pregunta formulada en una Evaluacion tomada
 * @author daxcurson
 *
 */
@Entity
@Table(name="preguntas_formuladas")
public class PreguntaFormulada implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2900347784134803657L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="evaluacion_tomada_id")
	private EvaluacionTomada evaluacion_tomada;
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
}
