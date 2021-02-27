package it.lea.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.Part;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.lea.entities.Answer;
import it.lea.entities.FilledForm;
import it.lea.entities.Product;
import it.lea.entities.Question;
import it.lea.entities.Questionnaire;
import it.lea.entities.User;
import it.lea.services.ProductService;
import it.lea.services.QuestionService;
import it.lea.services.QuestionnaireService;
import it.lea.services.UserService;
import it.lea.utils.ImageUtils;

@WebServlet("/CreateQuestionnaire")
public class CreateQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.lea.services/ProductService")
	private ProductService productService;
	@EJB(name = "it.lea.services/QuestionnaireService")
	private QuestionnaireService questionnaireService;
	@EJB(name = "it.lea.services/QuestionService")
	private QuestionService questionService;

	public CreateQuestionnaire() {
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
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			String loginpath = getServletContext().getContextPath() + "/index.html";
			response.sendRedirect(loginpath);
			return;
		}

		String productName = null;
		Date date = null;
		Part imgFile = null;
		byte[] imgByteArray = null;
		Integer questionsNum = null;
		List<Question> questions = null;
		List<String> questionsText = new ArrayList<String>();

		String path = "/WEB-INF/CreationPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

		try {

			productName = request.getParameter("product");
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) sdf.parse(request.getParameter("date"));
 
			/*
			 * TO DO :IMAGE UPLOAD
			 * --------------------------------------------------------------------------
			 */

			// imgFile = request.getPart("picture");
			// System.out.println(" 11111111111 " + imgFile.getSize());
			// InputStream imgContent = imgFile.getInputStream();
			// System.out.println(" 222222222 " + imgContent.hashCode());
			// imgByteArray = ImageUtils.readImage(imgContent);
			// System.out.println(" " + imgByteArray.length);

			questionsNum = (Integer) session.getAttribute("questionsNum");
			System.out.println("THE questionNUM IS " + questionsNum);

			for (int i = 0; i <= questionsNum; i++) {

				questionsText.add(request.getParameter(Integer.toString(i)));
 
			}

			System.out.println("la dataaaaaaaaaa " + date.compareTo(new Date(System.currentTimeMillis())));
			System.out.println("la dataaaaaaaaaa di oggiiiiiii " + new Date(System.currentTimeMillis()));

			if (productName == null || date.compareTo(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)) < 0
					|| /*
						 * imgByteArray.length == 0 ||
						 */ questionsNum == null || questionsText == null) {
				ctx.setVariable("message", "Please select the current or a future date");

				templateEngine.process(path, ctx, response.getWriter());
				return;
			}

		} catch (Exception e) {
			ctx.setVariable("message", "Error in creating the questionnaire");

			templateEngine.process(path, ctx, response.getWriter());
			return;
		}

		// Create Product

		Product product = null;

		try {
			product = productService.createProduct(imgByteArray, productName);
			questions = questionService.saveQuestions(questionsText);

			questionnaireService.saveQuestionnaire(date, product, questions);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRRRRRORRRREEEEE");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to create product");
			return;
		}

		/*
		 * **************** TO DO: 1) confirmation message 2)pulire sessione (num
		 * domande)
		 *********************************/

		ctx.setVariable("message", "The product and the questionnaire have been saved");
		templateEngine.process(path, ctx, response.getWriter());

	}

}
