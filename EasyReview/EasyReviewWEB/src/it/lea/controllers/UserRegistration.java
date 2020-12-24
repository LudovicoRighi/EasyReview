package it.lea.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lea.entities.User;
import it.lea.exceptions.CredentialsException;
import it.lea.services.UserService;

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "it.lea.services/UserService")
	private UserService usrService;

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usrn = null;
		String pwd = null;
		String confirmpwd = null;
		String email = null;
		usrn = request.getParameter("user");
		pwd = request.getParameter("pass");
		email = request.getParameter("email");
		confirmpwd = request.getParameter("confirmpass");
		try {
			if (usrn == null || pwd == null || confirmpwd == null || email == null || usrn.isEmpty() || pwd.isEmpty()
					|| confirmpwd.isEmpty() || email.isEmpty()) {
				if (!pwd.equals(confirmpwd)) {
					throw new Exception("Missing or empty value");
				}
			}
		} catch (Exception e) {
			//response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
			return;
		}
		
		User user;
		try {
			// query db to authenticate for user
			user = usrService.checkCredentials(usrn, pwd);
		} catch (CredentialsException | NonUniqueResultException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
			return;
		}
	}

}
