package LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/profilelink")
public class UserValidation extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication ? user=root & password=akshay@11");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@Override
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stubsss
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		//int userId=0;
		
		String query="select email,password from logininfo";
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			//rs=pstmt.executeQuery();
			boolean status=false;
			while(rs.next())
			{
				HttpSession ses=req.getSession();
				String mail=rs.getString(1);
				String pass=rs.getString(2);
				
				if(mail.equals(email) && pass.equals(password))
				{
					//RequestDispatcher rd=get;
					RequestDispatcher rd=req.getRequestDispatcher("profile.html");
					rd.forward(req, resp);
				}
			}
			if(!status)
			{
				RequestDispatcher rd=req.getRequestDispatcher("index.html");
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
