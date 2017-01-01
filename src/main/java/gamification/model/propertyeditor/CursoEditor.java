package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import gamification.model.Curso;
import gamification.service.CursoService;

public class CursoEditor extends PropertyEditorSupport 
{
	@Autowired
	private final CursoService cursoService;
	public CursoEditor(CursoService cursoService) 
	{
		this.cursoService=cursoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Curso p=cursoService.getCursoById(Integer.parseInt(text));
        setValue(p);
	}
}
