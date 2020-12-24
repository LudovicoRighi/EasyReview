package it.lea.services;

import java.util.List;
import it.lea.exceptions.RegistrationException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import it.lea.entities.User;
import it.lea.exceptions.CredentialsException;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;

	public UserService() {
	}

	public User checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");

	}

	public User registerUser(String usrn, String pwd, String email)
			throws RegistrationException {

		User user = new User(usrn, email, pwd);
		try {
			em.persist(user);
			em.flush();
		} catch (PersistenceException e) {
			throw new RegistrationException("Could not register the user");
		}
		return user;
		}

}
