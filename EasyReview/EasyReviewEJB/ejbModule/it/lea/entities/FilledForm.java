
package it.lea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "filled_form", schema = "db_easyr")

public class FilledForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "questionnaire_id")
	private Questionnaire questionnaire;

	@OneToMany(mappedBy= "form")
	private List<Answer> answers;
	


	private Integer age;
	private String sex;
	private String expertice;

	public FilledForm() {

	}
	
	
	

	public FilledForm(User user, Questionnaire questionnaire, List<Answer> answers, Integer age, String sex,
			String expertice) {
		super();
		this.user = user;
		this.questionnaire = questionnaire;
		this.answers = answers;
		this.age = age;
		this.sex = sex;
		this.expertice = expertice;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*
	 * public Questionnaire getQuestionnaire() { return questionnaire; }
	 * 
	 * public void setQuestionnaire(Questionnaire questionnaire) {
	 * this.questionnaire = questionnaire; }
	 * 
	 * public List<Answer> getAnswers() { return answers; }
	 * 
	 * public void setAnswers(List<Answer> answers) { this.answers = answers; }
	 */

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getExpertice() {
		return expertice;
	}

	public void setExpertice(String expertice) {
		this.expertice = expertice;
	}

}
