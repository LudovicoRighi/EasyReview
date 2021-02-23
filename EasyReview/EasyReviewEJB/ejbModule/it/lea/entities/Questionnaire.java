package it.lea.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
@NamedQuery(name = "Questionnaire.getQuestOfToday", query = "SELECT q FROM Questionnaire q WHERE q.date=CURRENT_DATE")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "questionnaire")
	private List<FilledForm> forms;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_questionnaire")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy="questionnaire")
	private List<Question> questions;
	

	public Questionnaire() {

	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<FilledForm> getForms() {
		return forms;
	}


	public void setForms(List<FilledForm> forms) {
		this.forms = forms;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

/*
	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	*/

}
