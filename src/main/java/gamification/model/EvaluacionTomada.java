package gamification.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="evaluaciones_tomadas")
public class EvaluacionTomada 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="curso_oferta_id")
	private CursoOferta cursoOferta;
	private Date fecha_evaluacion;
	@ManyToOne
	@JoinColumn(name="estudiante_id")
	private Estudiante estudiante;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CursoOferta getCursoOferta() {
		return cursoOferta;
	}
	public void setCursoOferta(CursoOferta cursoOferta) {
		this.cursoOferta = cursoOferta;
	}
	public Date getFecha_evaluacion() {
		return fecha_evaluacion;
	}
	public void setFecha_evaluacion(Date fecha_evaluacion) {
		this.fecha_evaluacion = fecha_evaluacion;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
}
