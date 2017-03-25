package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import gamification.model.Pregunta;
import gamification.service.PreguntaService;

public class PreguntaEditor extends PropertyEditorSupport
{
	@Autowired
	private PreguntaService preguntaService;
	private static Logger log=LogManager.getLogger(PreguntaEditor.class);

	public PreguntaEditor(PreguntaService p) 
	{
		this.preguntaService=p;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		log.trace("Estoy en PreguntaEditor, me llamaron con este texto: "+text);
		Pregunta p=preguntaService.getById(Integer.parseInt(text));
		log.trace("La pregunta encontrada es "+p);
		setValue(p);
	}

}
