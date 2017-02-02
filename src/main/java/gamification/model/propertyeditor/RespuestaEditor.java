package gamification.model.propertyeditor;
import java.beans.PropertyEditorSupport;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import gamification.model.Respuesta;

/**
 * Editor que convierte un string recibido del formulario en un objeto Respuesta. 
 * Lo que hacen otros editors es buscar en la base de datos el item cuyo id viene en el
 * string, pero aqui no tenemos que hacer eso. Aca tenemos que crear un nuevo objeto
 * Respuesta, y cargar el valor que viene en Text en ese objeto.
 * @author daxcurson
 *
 */
public class RespuestaEditor extends PropertyEditorSupport 
{
	private static Logger log=LogManager.getLogger(RespuestaEditor.class);

	public RespuestaEditor() 
	{
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Respuesta g=new Respuesta();
		log.trace("Respuesta recibida: "+text);
		g.setValor_respuesta(text);
        setValue(g);
	}
}
