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

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.lea.entities.Log;
import it.lea.entities.Question;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;
import it.lea.services.QuestionnaireService;
import it.lea.services.UserService;

@WebServlet("/QuestionnairePage")
public class GoToQuestionnairePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.lea.services/QuestionnaireService")
	private QuestionnaireService questionnaireService;
	@EJB(name = "it.lea.services/UserService")
	private UserService userService;

	private TemplateEngine templateEngine;

	public GoToQuestionnairePage() {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// If the user is not logged in (not present in session) redirect to the login
		String loginpath = getServletContext().getContextPath() + "/index.html";
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		}

		User user = (User) session.getAttribute("user");

		Boolean hasDoneQuestionnaire = null;
		Boolean isBanned = null;
		try {
			hasDoneQuestionnaire = userService.hasDoneDailyQuestionnaire(user.getId());
			isBanned = userService.checkIfBanned(user.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (isBanned == true) {

			request.getSession().setAttribute("errorMessage",
					"You have been banned, you cannot do other questionnaires!");

			String path = getServletContext().getContextPath() + "/Home";
			response.sendRedirect(path);

		}

		else if (hasDoneQuestionnaire == true) {
			request.getSession().setAttribute("errorMessage",
					"You have alredy completed the questionnaire of today, come back tomorrow!");

			String path = getServletContext().getContextPath() + "/Home";
			response.sendRedirect(path);
		}

		else {

			Questionnaire questionnaire = null;
			List<Question> questions = null;

			try {

				questionnaire = questionnaireService.getQuestionnaireOfToday();
				questions = questionnaire.getQuestions();
				Log log = userService.saveLog(user);

			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to get data");
				return;
			}

			String path = "/WEB-INF/QuestionnairePage.html";
			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
			ctx.setVariable("questions", questions);

			ctx.setVariable("size", questions.size() - 1);

			List<String> texts = new ArrayList<String>();

			if (session.getAttribute("answers") != null) {

				texts = (List<String>) session.getAttribute("answers");

			} else {
				for (int i = 0; i < questions.size(); i++) {
					texts.add(null);
				}
			}

			ctx.setVariable("answers", texts);

			templateEngine.process(path, ctx, response.getWriter());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}

}
