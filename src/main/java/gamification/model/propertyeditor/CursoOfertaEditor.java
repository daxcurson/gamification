package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import gamification.model.CursoOferta;
import gamification.service.CursoService;

public class CursoOfertaEditor extends PropertyEditorSupport 
{
	@Autowired
	private final CursoService cursoService;
	public CursoOfertaEditor(CursoService cursoService) 
	{
		this.cursoService=cursoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		CursoOferta p=cursoService.getCursoOfertaById(Integer.parseInt(text));
        setValue(p);
	}
}
