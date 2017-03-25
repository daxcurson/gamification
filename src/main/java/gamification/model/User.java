package gamification.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import gamification.model.validator.PasswordsEqualConstraint;

@Entity
@Table(name="users",uniqueConstraints = @UniqueConstraint(name = "username_uc"
,columnNames = "username"))
@PasswordsEqualConstraint(message = "Los passwords no coinciden")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7775568520866798787L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Column(unique=true)
	private String username;
	private String password;
	@Transient
	private String confirm_password;
	private int enabled;
	@OneToOne(mappedBy="user")
	private Persona persona;

	@OneToOne
	private Group group;
	
	public int getId()
	{
		return id;
	}
	public void setId(int i)
	{
		id=i;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String u)
	{
		username=u;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String p)
	{
		password=p;
	}
	public Group getGroup()
	{
		return this.group;
	}
	public void setGroup(Group s)
	{
		this.group=s;
	}
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
