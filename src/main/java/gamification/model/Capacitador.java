package gamification.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="capacitadores")
public class Capacitador extends Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2748449069620528033L;
}
