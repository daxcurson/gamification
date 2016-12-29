package gamification.model;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="estudiantes")
public class Estudiante 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// La persona puede tener cero o un usuario en el sistema.
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="user_id")
	private User user;
	private boolean usuario_sistema;
	@NotEmpty
	private String nombre;
	private int habilitada;
	
	/**
	 * Valores por defecto de persona. No es usuario del sistema y esta habilitada.
	 */
	public Estudiante()
	{
		this.usuario_sistema=false;
		this.habilitada=1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@JsonBackReference
	public boolean getUsuario_sistema() {
		return usuario_sistema;
	}
	public void setUsuario_sistema(boolean usuario_sistema) {
		this.usuario_sistema = usuario_sistema;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(int habilitada) {
		this.habilitada = habilitada;
	}
}
