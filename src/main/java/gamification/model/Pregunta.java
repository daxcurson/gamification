package gamification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="preguntas")
public class Pregunta 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="tipo_pregunta_id")
	private TipoPregunta tipoPregunta;
	private String textoPregunta;
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
}
