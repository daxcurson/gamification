package gamification.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import gamification.dao.RememberMeTokenRepositoryDAO;
import gamification.model.Token;

@Component 
public class CustomTokenRepository implements PersistentTokenRepository { 

@Autowired 
private RememberMeTokenRepositoryDAO tokenDao;
@Override 
public void createNewToken(PersistentRememberMeToken token) { 
tokenDao.createNewToken(new Token(token)); 
} 

@Override 
public void updateToken(String series, String tokenValue, Date lastUsed) { 
tokenDao.updateToken(series, tokenValue, lastUsed); 
} 

@Override 
public PersistentRememberMeToken getTokenForSeries(String seriesId) { 
System.out.println("seriesId:"+seriesId); 
Token token = tokenDao.getTokenForSeries(seriesId); 
if (token == null) { 
return null; 
} 
System.out.println("Token:"+token.getUsername()); 
return new PersistentRememberMeToken(token.getUsername(), 
token.getSeries(), token.getTokenValue(), token.getDate()); 
} 

@Override 
public void removeUserTokens(String username) { 
tokenDao.removeUserTokens(username); 
} 
} 