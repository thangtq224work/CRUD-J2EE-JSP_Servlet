package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Users")
@Entity
public class Account {
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password" ,nullable = false)
	private String password;
	
	@Column(name = "role",nullable = false)	
	private AccountRole role;
	
	@Column(name="deleted",nullable = false)	
	private Boolean deleted;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(String username, String password, AccountRole role, Boolean deleted) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.deleted = deleted;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", role=" + role + ", deleted=" + deleted
				+ "]";
	}

	

	
	
	
	
}
