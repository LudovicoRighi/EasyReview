package it.lea.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.RequestDispatcher;
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

/*import it.lea.entities.Answer;
import it.lea.entities.Product;
import it.lea.entities.Questionnaire;*/
import it.lea.entities.User;
import it.lea.exceptions.CredentialsException;
import it.lea.exceptions.RegistrationException;
import it.lea.services.UserService;
import java.sql.Date;
//import java.util.Date;

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TemplateEngine templateEngine;
	@EJB(name = "it.lea.services/UserService")
	private UserService usrService;

	public UserRegistration() {
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

		String usrn = null;
		String pwd = null;
		String confirm = null;
		String email = null;

		try {
			usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
			pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
			email = StringEscapeUtils.escapeJava(request.getParameter("email"));
			confirm = StringEscapeUtils.escapeJava(request.getParameter("confirm"));

			if (usrn == null || pwd == null || email == null || confirm == null || usrn.isEmpty() || pwd.isEmpty()
					|| confirm.isEmpty() || email.isEmpty()) {
				throw new RegistrationException("Missing or empty field value");
			}

		} catch (RegistrationException e) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing field value");
			return;
		}

		String path;
		if (!pwd.equals(confirm)) {

			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
			ctx.setVariable("errorMsg", "Passwords mismatch");
			path = "/WEB-INF/RegistrationPage.html";
			templateEngine.process(path, ctx, response.getWriter());

		} else {

			User user = null;

			try {
				// query db to authenticate for user
				user = usrService.registerUser(usrn, email, pwd);

			} catch (RegistrationException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
				return;
			}

			path = "/index.html";
			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

			templateEngine.process(path, ctx, response.getWriter());

		}

	}

	public void destroy() {
	}

}
