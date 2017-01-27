package gamification.model;

import java.util.Date;
import java.util.List;

/**
 * Representa una "instancia de curso", una oportunidad ofrecida de tomar el curso de,
 * digamos, Visual Basic. Estas oportunidades pueden haber sido asignadas por
 * el administrador, como que en determinada fecha este curso se puede tomar,
 * y por lo tanto esta ofrecido en esa fecha.
 *
 */
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="curso_ofertas")
public class CursoOferta 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	private Date fecha_comienzo;
	private Date fecha_fin;
	@OneToMany(mappedBy="curso_oferta")
	private List<Evaluacion> evaluaciones;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_comienzo() {
		return fecha_comienzo;
	}
	public void setFecha_comienzo(Date fecha_comienzo) {
		this.fecha_comienzo = fecha_comienzo;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	@JsonBackReference
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
}
