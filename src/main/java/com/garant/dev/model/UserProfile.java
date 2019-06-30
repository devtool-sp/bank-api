package com.garant.dev.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.garant.dev.model.UserProfile;
import com.garant.dev.model.UserProfileType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
@Entity
@Table(name="USER_PROFILE")
public class UserProfile implements Serializable{
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; 
 
    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
     
   // @ManyToMany(mappedBy = "userProfiles")
   // private Set<User> users;
 
/*
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		//result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		/*
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		*/
		return true;
	}
 
    
}
