package it.lea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.lea.entities.Answer;
import it.lea.entities.Question;
// import it.lea.entities.Answer;
// import it.lea.entities.Questionnaire;
import it.lea.entities.User; 

@Stateless
public class AnswerService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;
	
	
	public AnswerService() {
		
	}
	
	public Answer saveAnswer(String answer, Question question){
		Answer ans = null;
		if(answer!=null && question!=null) {
			
			ans = new Answer(answer, question);
			question.addAnswer(ans);
			em.persist(ans);
		}
		
		return ans;
		
	
	}


}
