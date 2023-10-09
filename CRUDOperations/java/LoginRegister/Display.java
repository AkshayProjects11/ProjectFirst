package LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/balancelink")
public class Display extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication ?user=root &password=akshay@11");
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
		String accountNumber=req.getParameter("number");
		int number=Integer.parseInt(accountNumber);
		
		PreparedStatement pstmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		String query="select account_balance from transactioin_data where account_number=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setDouble(1, number);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				double accountBalance=rs.getDouble(1);
				pw.print("<h1>"+"Your Account Balace is"+accountBalance+"</h1>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
