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
	private String comentarios;
	private Nota nota_pregunta;
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
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Nota getNota_pregunta() {
		return nota_pregunta;
	}
	public void setNota_pregunta(Nota nota) {
		this.nota_pregunta = nota;
	}
}
