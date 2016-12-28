package gamification.service;

import java.util.List;

import gamification.exceptions.GrupoExistenteException;
import gamification.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public List<Group> listGroups();
	public void save(Group group) throws GrupoExistenteException;
}
