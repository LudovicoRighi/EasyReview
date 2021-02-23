package it.lea.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.lea.entities.Product; 

@Stateless
public class ProductService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;
	
	public ProductService() {
		
	}
	
	public Product getProductOfToday() {
		Product prod = null;
		try {
			prod = em.createNamedQuery("Product.getProdOfToday", Product.class).getSingleResult();
		}catch (Exception e) {
			System.out.println("prodotto non trovato");
		}
		return prod;
	}
	
	
}
