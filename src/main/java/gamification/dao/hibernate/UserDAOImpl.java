package gamification.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamification.dao.UserDAO;
import gamification.model.User;

@Repository
public class UserDAOImpl implements UserDAO
{
	private static Logger log=LogManager.getLogger(UserDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByLogin(String login) 
	{
		log.trace("Estoy en UserDAOImpl.findByLogin, login pedido:"+login);
		return (User) sessionFactory.getCurrentSession().createQuery("from User where username='"+login+"'").getSingleResult();
	}

	@Override
	@Transactional
	public void save(User user) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User findById(Long userId) 
	{
		log.trace("Estoy en UserDAOImpl.findById");
		return (User) sessionFactory.getCurrentSession().createQuery("from User where id="+userId).getSingleResult();
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByLoginOpenId(String loginOpenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByFacebookId(Long facebookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() 
	{
		log.trace("Estoy en UserDAOImpl.listUsers");
		return (List<User>) sessionFactory.getCurrentSession().createQuery("from User").getResultList();
	}

}
