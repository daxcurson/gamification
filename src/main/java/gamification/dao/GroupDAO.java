package gamification.dao;

import java.util.List;
import gamification.model.*;

public interface GroupDAO 
{
	List<Group> listAllGroups();
	Group findGroupById(long id);
	void save(Group g);
}
