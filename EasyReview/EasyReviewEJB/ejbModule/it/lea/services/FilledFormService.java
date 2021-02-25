package it.lea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.lea.entities.Answer;
import it.lea.entities.FilledForm;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;

// import it.lea.entities.Questionnaire;

@Stateless
public class FilledFormService {
	@PersistenceContext(unitName = "EasyReviewEJB")
	private EntityManager em;

	public FilledFormService() {

	}

	 

	public FilledForm saveFilledForm(User user, Questionnaire questionnaire, List<Answer> answers, Integer age,
			String sex, String expertice) {

		FilledForm form = null;
		form = new FilledForm(user, questionnaire, age, sex, expertice);

		for (Answer a : answers) {

			form.addAnswer(a);
		}
		
		questionnaire.addFilledForm(form);
		
		user.getForms().add(form);
				
		em.persist(form);
		

		return form;

	}

}
