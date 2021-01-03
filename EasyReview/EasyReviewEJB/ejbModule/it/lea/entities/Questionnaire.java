package it.lea.entities;

import java.io.Serializable;
import java.util.List;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "questionnaire", schema = "db_easyr")
@NamedQuery(name = "Questionnaire.getQuestOfToday", query = "SELECT q FROM Questionnaire q WHERE q.dateQ=CURRENT_DATE")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "quest")
	private List<Answer> answers;
	@Temporal(TemporalType.DATE)
	private Date dateQ;
	@ManyToOne
	@JoinColumn(name = "prodId")
	private Product prod;
	private Integer nQMark;
	private String qMark1;
	private String qMark2;
	private String qMark3;
	private String qMark4;
	private String qMark5;
	private String qMark6;
	private String qMark7;
	private String qMark8;
	private String qMark9;
	private String qMark10;

	public Questionnaire() {

	}

	public Questionnaire(Date dateQ, Product prod, Integer nQMark, String qMark1, String qMark2, String qMark3,
			String qMark4, String qMark5, String qMark6, String qMark7, String qMark8, String qMark9, String qMark10) {
		super();
		this.dateQ = dateQ;
		this.prod = prod;
		this.nQMark = nQMark;
		this.qMark1 = qMark1;
		this.qMark2 = qMark2;
		this.qMark3 = qMark3;
		this.qMark4 = qMark4;
		this.qMark5 = qMark5;
		this.qMark6 = qMark6;
		this.qMark7 = qMark7;
		this.qMark8 = qMark8;
		this.qMark9 = qMark9;
		this.qMark10 = qMark10;
	}

	/*
	 * public List<Answer> getAnswers() { return answers; }
	 * 
	 * public void setAnswers(List<Answer> answers) { this.answers = answers; }
	 */

	public Date getDate() {
		return dateQ;
	}

	public void setDate(Date dateQ) {
		this.dateQ = dateQ;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public Integer getnQMark() {
		return nQMark;
	}

	public void setnQMark(Integer nQMark) {
		this.nQMark = nQMark;
	}

	public String getqMark1() {
		return qMark1;
	}

	public void setqMark1(String qMark1) {
		this.qMark1 = qMark1;
	}

	public String getqMark2() {
		return qMark2;
	}

	public void setqMark2(String qMark2) {
		this.qMark2 = qMark2;
	}

	public String getqMark3() {
		return qMark3;
	}

	public void setqMark3(String qMark3) {
		this.qMark3 = qMark3;
	}

	public String getqMark4() {
		return qMark4;
	}

	public void setqMark4(String qMark4) {
		this.qMark4 = qMark4;
	}

	public String getqMark5() {
		return qMark5;
	}

	public void setqMark5(String qMark5) {
		this.qMark5 = qMark5;
	}

	public String getqMark6() {
		return qMark6;
	}

	public void setqMark6(String qMark6) {
		this.qMark6 = qMark6;
	}

	public String getqMark7() {
		return qMark7;
	}

	public void setqMark7(String qMark7) {
		this.qMark7 = qMark7;
	}

	public String getqMark8() {
		return qMark8;
	}

	public void setqMark8(String qMark8) {
		this.qMark8 = qMark8;
	}

	public String getqMark9() {
		return qMark9;
	}

	public void setqMark9(String qMark9) {
		this.qMark9 = qMark9;
	}

	public String getqMark10() {
		return qMark10;
	}

	public void setqMark10(String qMark10) {
		this.qMark10 = qMark10;
	}

}
