package gamification.service;

import java.util.List;

import gamification.model.Group;

public interface PermissionService 
{
	public List<Group> listAllGroups();
	public String grantOrRevokePermission(Group g,String permission);
	public Group findGroupById(int id);
}
