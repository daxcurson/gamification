package gamification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int id;

	// La persona puede tener cero o un usuario en el sistema.
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="user_id")
	@JsonBackReference
	protected User user;
	protected boolean usuario_sistema;
	protected boolean habilitada;

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
	public boolean isHabilitada() {
		return habilitada;
	}
	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
}
