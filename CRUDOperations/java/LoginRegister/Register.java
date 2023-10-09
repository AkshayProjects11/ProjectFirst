package LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/registerlink")
public class Register extends HttpServlet{
	Connection con;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PreparedStatement pstmt;
		// TODO Auto-generated method stub
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String addhar=req.getParameter("addhar");
		String pan=req.getParameter("pan");
		String address=req.getParameter("address");
		PrintWriter pw=resp.getWriter();
		String query="insert into logininfo(name,email,addhar,pan,address,password) values(?,?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, addhar);
			pstmt.setString(4, pan);
			pstmt.setString(5, address);
			pstmt.setString(6, password);
			int count=pstmt.executeUpdate();
			pw.print("<h1>"+count+"Record Inserted successfully</h1>");
			if(count>0)
			{
				double balance=0;
				String query1="insert into transactioin_data(holder_name,account_balance) values(?,?)";
				pstmt=con.prepareStatement(query1);
				pstmt.setString(1, name);
				pstmt.setDouble(2, balance);
				int count2=pstmt.executeUpdate();
				pw.print("<h1>"+count2+"account created successfully</h1>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
