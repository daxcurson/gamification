package gamification.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import gamification.model.Group;
import gamification.service.GroupService;

public class GroupEditor extends PropertyEditorSupport {

	private final GroupService groupService;
	public GroupEditor(GroupService groupService) 
	{
		this.groupService=groupService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Group g=groupService.getById(Long.parseLong(text));
        setValue(g);
	}
}
