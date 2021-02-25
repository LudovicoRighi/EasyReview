package it.lea.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.lea.entities.Answer;
import it.lea.entities.FilledForm;
import it.lea.entities.Question;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;
import it.lea.services.AnswerService;
import it.lea.services.FilledFormService;
import it.lea.services.QuestionnaireService;
import it.lea.services.UserService;

@WebServlet("/ThanksPage")
public class GoToThanksPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.lea.services/QuestionnaireService")
	private QuestionnaireService questionnaireService;
	@EJB(name = "it.lea.services/AnswerService")
	private AnswerService answerService;
	@EJB(name = "it.lea.services/FilledFormService")
	private FilledFormService formService;
	@EJB(name = "it.lea.services/UserService")
	private UserService userService;

	public GoToThanksPage() {
		super();
	}

	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// If the user is not logged in (not present in session) redirect to the login
		String loginpath = getServletContext().getContextPath() + "/index.html";
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		}

		Questionnaire questionnaire = null;
		List<Question> questions = null;
		List<String> answers = null;
		List<Answer> responses = null;
		User user = null;
		String sex = null;
		String age = null;
		String expertice = null;
		FilledForm form = null;
		Boolean banned = null;

		try {

			answers = (List<String>) session.getAttribute("answers");
			responses = new ArrayList<Answer>();
			user = (User) session.getAttribute("user");	
			sex = StringEscapeUtils.escapeJava(request.getParameter("sex"));
			age = StringEscapeUtils.escapeJava(request.getParameter("age"));
			expertice = StringEscapeUtils.escapeJava(request.getParameter("expertice"));
			questionnaire = questionnaireService.getQuestionnaireOfToday();
			questions = questionnaire.getQuestions();
			banned= userService.checkIfBanned(user.getId());
	 		

			form = formService.saveFilledForm(user, questionnaire, responses, Integer.valueOf(age), sex, expertice);
			// form = new FilledForm(user, questionnaire, responses, Integer.valueOf(age), sex, expertice);

			responses = answerService.saveAnswers(answers, questions, form);

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to get data");
			return;
		}

		String path = "/WEB-INF/ThanksPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("banned", banned);
		
		templateEngine.process(path, ctx, response.getWriter());

	}

}
