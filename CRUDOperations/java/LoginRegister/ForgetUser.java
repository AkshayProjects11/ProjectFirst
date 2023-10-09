package LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/forgetlink")
public class ForgetUser extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication?user=root & password=akshay@11");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PreparedStatement pstmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		String addhar=req.getParameter("addhar");
		
		String query="select password,addhar from logininfo";
		try {
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
		    while(rs.next())
		    {
		    	String addharId=rs.getString("addhar");
		    	String pass=rs.getString("password");
		    	if (addharId.equals(addhar)) {
					pw.print(pass);
				}
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
