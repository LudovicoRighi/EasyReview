package it.lea.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
public class ProvaService {
	@EJB(name = "examplePackage/ProvaService")
	 public String sayHello(String name) {
	        return "ciaooo, "  + name;
	    }
}
