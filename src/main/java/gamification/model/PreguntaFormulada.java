package gamification.model;

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
public class PreguntaFormulada 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="evaluacion_tomada_id")
	private EvaluacionTomada evaluacionTomada;
}
