package provaPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import examplePackage.ProvaService;
/**
 * Servlet implementation class provaServlet
 */
@WebServlet("/provaServlet")
public class provaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "examplePackage/ProvaService")
	private ProvaService pService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public provaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse
    		response)
    		throws ServletException, IOException {
    		 response.setContentType("text/plain");
    		 PrintWriter out = response.getWriter();
				out.println("Hello this is a test");
				out.println(pService.sayHello("ludovico"));
				out.close();
    		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
