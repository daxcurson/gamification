package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import gamification.model.TipoPregunta;
import gamification.service.PreguntaService;

public class TipoPreguntaEditor extends PropertyEditorSupport 
{
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
