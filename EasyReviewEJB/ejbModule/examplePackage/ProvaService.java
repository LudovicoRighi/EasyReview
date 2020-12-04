package examplePackage;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
public class ProvaService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	@EJB(name = "examplePackage/ProvaService")
	 public String sayHello(String name) {
	        return "ciaooo, "  + name;
	    }
}
