package gamification.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="cursos")
public class Curso implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3517769445392068551L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nombre;
	private String codigo_curso;
	@ManyToOne
	@JoinColumn(name="capacitador_id")
	private Capacitador capacitador;
	@OneToMany(targetEntity=CursoOferta.class,mappedBy="curso")
	private List<CursoOferta> ofertas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo_curso() {
		return codigo_curso;
	}
	public void setCodigo_curso(String codigo) {
		this.codigo_curso = codigo;
	}
	public Capacitador getCapacitador() {
		return capacitador;
	}
	public void setCapacitador(Capacitador capacitador) {
		this.capacitador = capacitador;
	}
	public List<CursoOferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<CursoOferta> ofertas) {
		this.ofertas = ofertas;
	}
	
}
