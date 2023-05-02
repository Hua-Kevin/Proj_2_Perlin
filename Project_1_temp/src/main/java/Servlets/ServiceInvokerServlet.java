package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServiceSelectorServlet
 */
@WebServlet("/ServiceInvokerServlet")
public class ServiceInvokerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceInvokerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//Needs to be here not in LogInServlet.java
		HttpSession session= request.getSession();
		
		try {
			session.setAttribute("input", java.net.URLEncoder.encode(request.getParameter("theInput"), "UTF-8").replace("+", "%20"));
			request.setAttribute("input", java.net.URLEncoder.encode(request.getParameter("theInput"), "UTF-8").replace("+", "%20"));
		} catch (Exception e) {
			
		}
        
		
		System.out.println(java.net.URLEncoder.encode(request.getParameter("theInput"), "UTF-8").replace("+", "%20"));
		System.out.println(session.getAttribute("input"));
		System.out.println(request.getAttribute("input"));

		RequestDispatcher rd = request.getRequestDispatcher("ServiceInvokerPage.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
