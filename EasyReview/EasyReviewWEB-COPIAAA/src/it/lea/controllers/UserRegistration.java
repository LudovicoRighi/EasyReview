package it.lea.controllers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import it.lea.exceptions.RegistrationException;
import it.lea.services.UserService;

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
		String path = null;

		path = "/WEB-INF/RegistrationPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

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

			ctx.setVariable("errorMsg", "Error in the registration process");
			templateEngine.process(path, ctx, response.getWriter());
			return;
		}

		if (!pwd.equals(confirm)) {

			ctx.setVariable("errorMsg", "Passwords mismatch");
			templateEngine.process(path, ctx, response.getWriter());

		} else {

			try {
				// query db to authenticate for user
				usrService.registerUser(usrn, email, pwd);

			} catch (RegistrationException e) {
				ctx.setVariable("errorMsg", "The username and/or email selected is not available");
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}
			ctx.setVariable("errorMsg", "The user has been successfully registered!");

			templateEngine.process(path, ctx, response.getWriter());

		}     

	}

	public void destroy() {
	}

}
