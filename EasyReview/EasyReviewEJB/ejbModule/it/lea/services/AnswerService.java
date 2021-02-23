package it.lea.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

import it.lea.entities.Answer;
import it.lea.entities.Questionnaire;
import it.lea.entities.User; 

@Stateless
public class AnswerService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;
	
	
	public AnswerService() {
		
	}

	/*
	public Answer insertAnswer() throws Exception{
		User user = em.find(User.class, 1);
		Questionnaire quest = em.find(Questionnaire.class, 1);
		Answer ans = new Answer(user,quest, 22, "m", "high", "risp1", "risp2", "risp3", null,null,null,null,null,null,null );
		try {
			em.persist(ans);
			em.flush();

		} catch (Exception e) {
			throw new Exception("Could not add a new answer");
		}
		return ans;
	}
	*/

}
