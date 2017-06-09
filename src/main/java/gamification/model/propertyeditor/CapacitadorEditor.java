package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import gamification.model.Capacitador;
import gamification.service.CapacitadorService;

public class CapacitadorEditor extends PropertyEditorSupport 
{
	@Autowired
	private final CapacitadorService capacitadorService;
	public CapacitadorEditor(CapacitadorService capacitadorService) 
	{
		this.capacitadorService=capacitadorService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Capacitador p=capacitadorService.getCapacitadorById(Integer.parseInt(text));
        setValue(p);
	}
}
