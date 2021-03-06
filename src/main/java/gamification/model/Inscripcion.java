package gamification.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="inscripciones")
public class Inscripcion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5408664970943991434L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne
	@JoinColumn(name="curso_oferta_id")
	private CursoOferta curso_oferta;
	@ManyToOne(targetEntity=Estudiante.class,fetch=FetchType.EAGER)
	@JoinColumn(name="estudiante_id")
	private Estudiante estudiante;
	private Date fecha_inscripcion;
	private boolean activa;
	@OneToMany(targetEntity=EvaluacionTomada.class,mappedBy="inscripcion",fetch=FetchType.LAZY)
	private List<EvaluacionTomada> evaluaciones;
	private boolean aprobada;
	private int puntos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonBackReference
	public CursoOferta getCurso_oferta() {
		return curso_oferta;
	}
	public void setCurso_oferta(CursoOferta curso) {
		this.curso_oferta = curso;
	}
	@JsonBackReference
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante persona) {
		this.estudiante = persona;
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
	public List<EvaluacionTomada> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(List<EvaluacionTomada> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	public boolean isAprobada() {
		return aprobada;
	}
	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
