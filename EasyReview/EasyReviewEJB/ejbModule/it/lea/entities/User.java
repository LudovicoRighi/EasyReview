package it.lea.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.security.enterprise.credential.Password;

@Entity
@Table(name = "usr", schema = "db_easyr")
//@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2")
public class User implements Serializable {
	@Id
	private Integer id;
	private String username;
	private String email;
	private Password password;
	private Boolean banned;
	private Boolean hasQOT;
	private Integer pointOfToday;
	private Integer totalPoints;
	private Boolean administrator; 
}
