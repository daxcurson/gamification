package gamification.model;

import javax.persistence.*;

@Entity
@Table(name="evaluaciones")
public class Evaluacion 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="curso_oferta_id")
	private CursoOferta curso_oferta;
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
}
