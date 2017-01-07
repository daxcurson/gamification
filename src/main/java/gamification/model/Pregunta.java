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
	private TipoPregunta tipo_pregunta;
	@Column(name="texto_pregunta")
	private String texto_pregunta;
	@ManyToOne
	@JoinColumn(name="evaluacion_id")
	private Evaluacion evaluacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto_pregunta() {
		return texto_pregunta;
	}
	public void setTexto_pregunta(String textoPregunta) {
		this.texto_pregunta = textoPregunta;
	}
	public TipoPregunta getTipo_pregunta() {
		return tipo_pregunta;
	}
	public void setTipo_pregunta(TipoPregunta tipoPregunta) {
		this.tipo_pregunta = tipoPregunta;
	}
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
}
