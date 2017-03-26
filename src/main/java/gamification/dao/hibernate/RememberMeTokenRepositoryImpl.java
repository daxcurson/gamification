package gamification.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import gamification.dao.RememberMeTokenRepositoryDAO;
import gamification.model.Token;

@Repository
public class RememberMeTokenRepositoryImpl implements PersistentTokenRepository,RememberMeTokenRepositoryDAO
{
	private static Logger log=LogManager.getLogger(RememberMeTokenRepositoryImpl.class);
	@Autowired
	private SessionFactory session;
	@Override
	@Transactional
	public void createNewToken(Token token) 
	{
		session.getCurrentSession().save(token);
	}
	@Override
	@Transactional
	public void createNewToken(PersistentRememberMeToken arg0) 
	{
		Token t=new Token();
		t.setDate(arg0.getDate());
		t.setSeries(arg0.getSeries());
		t.setTokenValue(arg0.getTokenValue());
		t.setUsername(arg0.getUsername());
		session.getCurrentSession().save(t);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) 
	{
		Token existingToken = (Token) session.getCurrentSession().get(Token.class, series);
		existingToken.setTokenValue(tokenValue);
		existingToken.setDate(lastUsed);
		session.getCurrentSession().merge(existingToken);
	}

	@Override
	public Token getTokenForSeries(String seriesId) 
	{
		return (Token) session.getCurrentSession().get(Token.class, seriesId); 
	}

	@Override
	@Transactional
	public void removeUserTokens(String username) 
	{
		log.trace("Borrando token!!");
		@SuppressWarnings("unchecked")
		List<Token> tokens=session.getCurrentSession().createQuery("from Token where username='"+username+"'").getResultList();
		for(Token t:tokens)
		{
			if(t!=null)
			{
				log.trace("Me toca borrar la token "+t.getTokenValue()+" del usuario "+t.getUsername());
				session.getCurrentSession().delete(t);
			}
			else
			{
				log.trace("No encontre tokens para borrar del usuario "+username);
			}
		}
	}
}
