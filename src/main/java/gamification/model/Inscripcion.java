package gamification.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inscripciones")
public class Inscripcion 
{
	private int id;
	@ManyToOne
	@JoinColumn(name="curso_oferta_id")
	private CursoOferta cursoOferta;
	@ManyToOne
	@JoinColumn(name="estudiante_id")
	private Estudiante estudiante;
	private Date fecha_inscripcion;
	private boolean activa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CursoOferta getCursoOferta() {
		return cursoOferta;
	}
	public void setCursoOferta(CursoOferta curso) {
		this.cursoOferta = curso;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}
	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
}
