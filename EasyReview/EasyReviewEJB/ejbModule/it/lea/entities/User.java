package it.lea.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usr", schema = "db_easyr")
@NamedQueries({
		@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u  WHERE u.username = ?1 and u.password = ?2"),
		@NamedQuery(name = "User.getLeaderboard", query = "SELECT u FROM User u ORDER BY u.id DESC") })

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String email;
	private String password;
	private Boolean banned;
	private Integer totalPoints;

	@OneToMany(mappedBy = "user")
	private List<FilledForm> forms;

	@OneToMany(mappedBy = "user")
	private List<Log> logs;

	public User() {

	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.banned = false;
		this.totalPoints = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	/*
	 * public List<FilledForm> getForms() { return forms; }
	 * 
	 * public void setForms(List<FilledForm> forms) { this.forms = forms; }
	 * 
	 * public List<Log> getLogs() { return logs; }
	 * 
	 * public void setLogs(List<Log> logs) { this.logs = logs; }
	 */

}
