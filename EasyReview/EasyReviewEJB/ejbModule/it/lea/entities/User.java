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
		@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2"),
 })

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
 	
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<FilledForm> forms;

		// @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
		// private List<Answer> answers;

	public User() {

	}
	
	

	
}
