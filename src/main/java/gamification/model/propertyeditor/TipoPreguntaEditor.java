package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import gamification.model.TipoPregunta;
import gamification.service.PreguntaService;

public class TipoPreguntaEditor extends PropertyEditorSupport 
{
	@Autowired
	private PreguntaService preguntaService;
	public TipoPreguntaEditor(PreguntaService service) 
	{
		this.preguntaService=service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		TipoPregunta g=preguntaService.getTipoPreguntaById(Integer.parseInt(text));
        setValue(g);
	}
}
