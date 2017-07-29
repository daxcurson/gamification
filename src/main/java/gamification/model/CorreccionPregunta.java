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

@Entity
@Table(name="correcciones_preguntas")
public class CorreccionPregunta implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1773028900718065790L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="correccion_id")
	private Correccion correccion;
	@ManyToOne
	@JoinColumn(name="respuesta_id")
	private Respuesta respuesta;
	@ManyToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	private String comentarios;
	private Nota nota;
	public Correccion getCorreccion() {
		return correccion;
	}
	public void setCorreccion(Correccion correccion) {
		this.correccion = correccion;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
}
