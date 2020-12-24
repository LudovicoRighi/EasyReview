package it.lea.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answer", schema = "db_easyr")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "questId")
	private Questionnaire quest;
	private Integer age;
	private String sex;
	private String expertice;
	private String aMark1;
	private String aMark2;
	private String aMark3;
	private String aMark4;
	private String aMark5;
	private String aMark6;
	private String aMark7;
	private String aMark8;
	private String aMark9;
	private String aMark10;

	public Answer() {

	}

	public Answer(User user, Questionnaire quest, Integer age, String sex, String expertice, String aMark1,
			String aMark2, String aMark3, String aMark4, String aMark5, String aMark6, String aMark7, String aMark8,
			String aMark9, String aMark10) {
		super();
		this.user = user;
		this.quest = quest;
		this.age = age;
		this.sex = sex;
		this.expertice = expertice;
		this.aMark1 = aMark1;
		this.aMark2 = aMark2;
		this.aMark3 = aMark3;
		this.aMark4 = aMark4;
		this.aMark5 = aMark5;
		this.aMark6 = aMark6;
		this.aMark7 = aMark7;
		this.aMark8 = aMark8;
		this.aMark9 = aMark9;
		this.aMark10 = aMark10;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Questionnaire getQuest() {
		return quest;
	}

	public void setQuest(Questionnaire quest) {
		this.quest = quest;
	}

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

	public String getaMark1() {
		return aMark1;
	}

	public void setaMark1(String aMark1) {
		this.aMark1 = aMark1;
	}

	public String getaMark2() {
		return aMark2;
	}

	public void setaMark2(String aMark2) {
		this.aMark2 = aMark2;
	}

	public String getaMark3() {
		return aMark3;
	}

	public void setaMark3(String aMark3) {
		this.aMark3 = aMark3;
	}

	public String getaMark4() {
		return aMark4;
	}

	public void setaMark4(String aMark4) {
		this.aMark4 = aMark4;
	}

	public String getaMark5() {
		return aMark5;
	}

	public void setaMark5(String aMark5) {
		this.aMark5 = aMark5;
	}

	public String getaMark6() {
		return aMark6;
	}

	public void setaMark6(String aMark6) {
		this.aMark6 = aMark6;
	}

	public String getaMark7() {
		return aMark7;
	}

	public void setaMark7(String aMark7) {
		this.aMark7 = aMark7;
	}

	public String getaMark8() {
		return aMark8;
	}

	public void setaMark8(String aMark8) {
		this.aMark8 = aMark8;
	}

	public String getaMark9() {
		return aMark9;
	}

	public void setaMark9(String aMark9) {
		this.aMark9 = aMark9;
	}

	public String getaMark10() {
		return aMark10;
	}

	public void setaMark10(String aMark10) {
		this.aMark10 = aMark10;
	}
	

}
