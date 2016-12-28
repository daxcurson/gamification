package gamification.dao.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gamification.dao.GroupDAO;
import gamification.model.Group;

@Repository
public class GroupDAOImpl implements GroupDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> listAllGroups() 
	{
		return sessionFactory.getCurrentSession().createQuery("from Group").getResultList();
	}

	@Override
	public Group findGroupById(long id) 
	{
		return (Group)sessionFactory.getCurrentSession().createQuery("from Group where id="+id).getSingleResult();
	}

	@Override
	public void save(Group g) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(g);
	}
}
