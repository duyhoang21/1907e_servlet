package class3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import controller.UserController;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class ConnectJDBCServlet
 */
@WebServlet("/ConnectJDBCServlet")
public class ConnectJDBCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(ConnectJDBCServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectJDBCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("Connecting JDBC...");
		
		LOGGER.info("Connecting to JDBC...");
		
		try {
			UserController controller =  new UserController();
			List<User> list = controller.getAllUsers();
			
			out.println("<table border='1'><tr><td>ID</td><td>Name</td><td>EMail</td><td>Country</td></tr>");
			for (User user : list) {
				out.println("<tr><td>" + user.getId() +"</td>");
				out.println("<td>" + user.getName() +"</td>");
				out.println("<td>" + user.getEmail() +"</td>");
				out.println("<td>" + user.getCountry() +"</td></tr>");
			}
			out.println("</table>");
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
