package it.lea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "db_easyr")
@NamedQuery(name = "Product.getProdOfToday", query = "SELECT p FROM Questionnaire q JOIN q.prod p WHERE q.dateQ=CURRENT_DATE")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "prod", cascade = { CascadeType.ALL })
	private List<Questionnaire> questionnaires;
	@OneToMany(mappedBy = "prod", cascade = { CascadeType.ALL })
	private List<Review> reviews;
	private String pathToImage;
	private String pName;

	public Product() {

	}

	public Product(String pathToImage, String pName) {
		super();  
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
