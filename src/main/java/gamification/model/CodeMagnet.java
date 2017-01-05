package gamification.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * CodeMagnet divide el texto en fragmentos y permite reordenarlo. 
 * @author anali
 *
 */
@Entity
@DiscriminatorValue("1")
public class CodeMagnet extends Pregunta
{
	private String texto_ordenar;

	public String getTexto_ordenar() {
		return texto_ordenar;
	}

	public void setTexto_ordenar(String texto_ordenar) {
		this.texto_ordenar = texto_ordenar;
	}
}
