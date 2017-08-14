package gamification.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="configuraciones")
public class Configuracion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1641511007098200936L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String config_nombre;
	private String config_valor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfig_nombre() {
		return config_nombre;
	}
	public void setConfig_nombre(String config_nombre) {
		this.config_nombre = config_nombre;
	}
	public String getConfig_valor() {
		return config_valor;
	}
	public void setConfig_valor(String config_valor) {
		this.config_valor = config_valor;
	}
}
