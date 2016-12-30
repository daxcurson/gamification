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
 * Una respuesta a una pregunta formulada en una Evaluacion Tomada
 * @author daxcurson
 *
 */
@Entity
@Table(name="respuestas")
public class Respuesta 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	private String valor_respuesta;
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
}
