package it.lea.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username= request.getParameter("username");
		 String password= request.getParameter("password");
		 if(username.equals("Ludovico") && password.equals("ciao")) {
			 System.out.println("okok");
			 request.setAttribute("palla", username);
			 RequestDispatcher rd = request.getRequestDispatcher("ShowInfo.jsp");
			 rd.forward(request, response);
		 }
		response.getWriter().append("Served cia come stai: ").append(request.getContextPath());
	}
	

		 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
