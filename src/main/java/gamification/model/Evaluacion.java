package gamification.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="evaluaciones")
public class Evaluacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3417716467903047138L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="curso_oferta_id")
	private CursoOferta curso_oferta;
	@OneToMany(targetEntity=Pregunta.class,cascade={CascadeType.ALL},mappedBy="evaluacion")
	private List<Pregunta> preguntas=new LinkedList<Pregunta>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CursoOferta getCurso_oferta() {
		return curso_oferta;
	}
	public void setCurso_oferta(CursoOferta cursoOferta) {
		this.curso_oferta = cursoOferta;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
