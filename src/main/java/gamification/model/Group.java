package gamification.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="groups")
public class Group implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2295196331571546077L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="group_name")
	private String group_name;
	
	@Column(name="created")
	private Date created;
	
	@Column(name="modified")
	private Date modified;
	
    @OneToMany(mappedBy="group",orphanRemoval=true)
    @Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE})
    private Set<Permission> permissions=new HashSet<Permission>();

	
	public int getId()
	{
		return id;
	}
	public void setId(int i)
	{
		id=i;
	}
	public String getGroup_name()
	{
		return this.group_name;
	}
	public void setGroup_name(String g)
	{
		group_name=g;
	}
	public Date getCreated()
	{
		return this.created;
	}
	public void setCreated(Date d)
	{
		created=d;
	}
	public Date getModified()
	{
		return this.modified;
	}
	public void setModified(Date d)
	{
		modified=d;
	}
	/**
	 * @return the permissions
	 */
	public Set<Permission> getPermissions() {
		return permissions;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
