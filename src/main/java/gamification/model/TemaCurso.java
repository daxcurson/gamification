package gamification.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Tema de un curso, sobre el que se hacen preguntas.
 * @author anali
 *
 */
@Entity
@Table(name="temas_cursos")
public class TemaCurso implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818515031270953292L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	private String nombre;
	private String descripcion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
