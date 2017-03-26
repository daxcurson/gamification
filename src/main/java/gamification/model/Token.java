package gamification.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

@Entity 
@Table(name = "persistent_logins") 
public class Token extends PersistentRememberMeToken
{ 

private String series; 
private String username; 
private String token; 
private Date last_used; 

public Token()
{
	super("","","",new Date());
}
public Token(String username, String series, String tokenValue, Date date) 
{
	super(username,series,tokenValue,date);
	this.series=series;
	this.username=username;
	this.token=tokenValue;
	this.last_used=date;
}

public Token(PersistentRememberMeToken persistentRememberMeToken) {
	
	super(persistentRememberMeToken.getUsername(),
			persistentRememberMeToken.getSeries(),
			persistentRememberMeToken.getTokenValue(),
			persistentRememberMeToken.getDate());
	this.username = persistentRememberMeToken.getUsername(); 
	this.series = persistentRememberMeToken.getSeries(); 
	this.last_used = persistentRememberMeToken.getDate(); 
	this.token = persistentRememberMeToken.getTokenValue(); 
} 

@Column(name = "username") 
public String getUsername() { 
return username; 
} 

public void setUsername(String username) { 
this.username = username; 
} 

@Id 
@Column(name = "series") 
public String getSeries() { 
return series; 
} 

public void setSeries(String series) { 
this.series = series; 
} 

@Column(name = "token") 
public String getTokenValue() { 
return token; 
} 

public void setTokenValue(String tokenValue) { 
this.token = tokenValue; 
} 

@Column(name = "last_used") 
public Date getDate() { 
return last_used; 
} 

public void setDate(Date last_used) { 
this.last_used = last_used; 
} 
} 