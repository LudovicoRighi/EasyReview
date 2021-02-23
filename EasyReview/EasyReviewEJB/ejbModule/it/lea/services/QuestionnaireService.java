package it.lea.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.lea.entities.Questionnaire;
 
// import it.lea.entities.Questionnaire;

@Stateless
public class QuestionnaireService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;
	
	public QuestionnaireService() {
		
	}
	
	public Questionnaire getQuestionnaireOfToday() {
		Questionnaire quest = null;
		try {
			quest = em.createNamedQuery("Questionnaire.getQuestOfToday", Questionnaire.class).getSingleResult();
		}catch (Exception e) {
			System.out.println("questionario non trovato");
		}
		return quest;
	}
}
