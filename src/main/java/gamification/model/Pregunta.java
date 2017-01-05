package gamification.model;

import javax.persistence.*;

@Entity
@Table(name="preguntas")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_pregunta_id",discriminatorType=DiscriminatorType.INTEGER)
public class Pregunta 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="tipo_pregunta_id")
	private TipoPregunta tipoPregunta;
	@Column(name="texto_pregunta")
	private String textoPregunta;
	@ManyToOne
	@JoinColumn(name="evaluacion_id")
	private Evaluacion evaluacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTextoPregunta() {
		return textoPregunta;
	}
	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}
	public TipoPregunta getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
}
