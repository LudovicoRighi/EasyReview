package it.lea.entities;

import java.io.Serializable;
 
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
 import javax.persistence.Table;

@Entity
@Table(name = "usr", schema = "db_easyr")
@NamedQueries({
		@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2"),
		@NamedQuery(name = "User.getLeaderboard", query = "SELECT r FROM User r WHERE r.hasQOT=true ORDER BY r.pointOfToday DESC") 
})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	// private List<Answer> answers;

	// @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	// private List<Answer> answers;
	private String username;
	private String email;
	private String password;
	private Boolean banned;
	private Boolean hasQOT;
	private Integer pointOfToday;
	private Integer totalPoints;
	private Boolean administrator;

	public User() {

	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.banned = false;
		this.hasQOT = false;
		this.pointOfToday = 0;
		this.totalPoints = 0;
		this.administrator = false;

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

	public Boolean getHasQOT() {
		return hasQOT;
	}

	public void setHasQOT(Boolean hasQOT) {
		this.hasQOT = hasQOT;
	}

	public Integer getPointOfToday() {
		return pointOfToday;
	}

	public void setPointOfToday(Integer pointOfToday) {
		this.pointOfToday = pointOfToday;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

}
