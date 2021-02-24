package it.lea.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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

import it.lea.entities.Question;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;
import it.lea.services.QuestionnaireService;

@WebServlet("/StatisticsPage")
public class GoToStatisticsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.lea.services/QuestionnaireService")
	private QuestionnaireService questionnaireService;

	public GoToStatisticsPage() {
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

		try {

			questionnaire = questionnaireService.getQuestionnaireOfToday();
			questions = questionnaire.getQuestions();

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to get data");
			return;
		}

		/* INIZIO */
		List<String> answers = new ArrayList<String>();
		for (Question q : questions) {

			answers.add(request.getParameter(Integer.toString(q.getId()))); 
		}

		/* FINE */

		String path = "/WEB-INF/StatisticsPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		request.getSession().setAttribute("answers", answers);

		templateEngine.process(path, ctx, response.getWriter());

	}

}
