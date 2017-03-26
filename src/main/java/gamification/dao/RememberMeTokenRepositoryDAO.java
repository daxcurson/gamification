package gamification.dao;

import java.util.Date;

import gamification.model.Token;

public interface RememberMeTokenRepositoryDAO 
{
	void createNewToken(Token token);
	void updateToken(String series, String tokenValue, Date lastUsed);
	Token getTokenForSeries(String seriesId);
	void removeUserTokens(String username);
}
