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
@Table(name = "review", schema = "db_easyr")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "prodId")
	private Product prod;
	private String rText;

	public Review() {

	}

	public Review(Product prod, String rText) {
		super();
		this.prod = prod;
		this.rText = rText;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public String getrText() {
		return rText;
	}

	public void setrText(String rText) {
		this.rText = rText;
	}

}
