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
	private CursoOferta cursoOferta;
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
	public CursoOferta getCursoOferta() {
		return cursoOferta;
	}
	public void setCursoOferta(CursoOferta cursoOferta) {
		this.cursoOferta = cursoOferta;
	}
}
