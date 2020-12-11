package com.lra;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lra.dao.PersonDao;
import com.lra.model.Person;

/**
 * Servlet implementation class GetPersonController
 */
public class GetPersonController extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int value = Integer.parseInt(request.getParameter("ludo"));
		
		PersonDao dao = new PersonDao();
		
		Person p1 = dao.getPerson(value);
		
		request.setAttribute("person", p1);
		
		RequestDispatcher rd = request.getRequestDispatcher("showPerson.jsp");
		rd.forward(request,response);
		
	}

	
	

}
