package it.lea.services;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import it.lea.entities.Answer;
import it.lea.entities.FilledForm;
import it.lea.entities.Product;
import it.lea.entities.Question;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;

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
		} catch (Exception e) {
			System.out.println("questionario non trovato");
		}
		return quest;
	}

	public Questionnaire saveQuestionnaire(Date date, Product product, List<Question> questions) throws Exception {

		System.out.println("entro in SaveQUestionnaire");

		Questionnaire quest = null;
		quest = new Questionnaire(date, product);

		for (Question q : questions) {

			quest.addQuestion(q);
		}

		// product.getQuestionnaires().add(quest);

		try {
			em.persist(quest);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error creating the questionnaire");
		}

		System.out.println("esco in SaveQUestionnaire");

		return quest;

	}

	public void deleteQuestionnaire(Date date) throws Exception {

		Questionnaire questionnaire = null;

		try {
			questionnaire = em.createNamedQuery("Questionnaire.getQuestByDate", Questionnaire.class)
					.setParameter(1, date).getSingleResult();
		} catch (Exception e) {
			throw new Exception("Error searching the questionnaire");
		}

		em.remove(questionnaire);

	}

}
