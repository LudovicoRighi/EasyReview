package it.lea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "product", schema = "db_easyr")
public class Product implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "prod", cascade = { CascadeType.ALL })
	private List<Questionnaire> questionnaires;
	@OneToMany(mappedBy = "prod", cascade = { CascadeType.ALL })
	private List<Review> reviews ;
	private String pathToImage;
	private String pName;
	
	public Product() {
		
	}
	public Product(List<Questionnaire> questionnaires, List<Review> reviews, String pathToImage, String pName) {
		super();
		this.questionnaires = questionnaires;
		this.reviews = reviews;
		this.pathToImage = pathToImage;
		this.pName = pName;
	}
	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}
	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public String getPathToImage() {
		return pathToImage;
	}
	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	
}
