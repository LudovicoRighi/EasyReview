package it.lea.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.lea.entities.Answer;
import it.lea.entities.FilledForm;
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

	public List<Answer> saveAnswers(List<String> answers, List<Question> questions, FilledForm form) {

		List<Answer> ans = new ArrayList<Answer>();
		if (answers != null && questions != null) {

			for (int i = 0; i < answers.size(); i++) {
				ans.add(new Answer(answers.get(i), questions.get(i), form));
				form.addAnswer(ans.get(i));
				// responses.add(a);
			}
			/*
			 * ans = new Answer(answer, question, form); question.addAnswer(ans);
			 * form.addAnswer(ans);
			 */
			em.merge(form);
		}

		return ans;

	}

}
