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
@WebServlet("/depositlink")
public class Deposit extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication? user=root & password=akshay@11");
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
		String amount=req.getParameter("amt");
		String acNumber=req.getParameter("number");
		int number=Integer.parseInt(acNumber);
		double amt=Double.parseDouble(amount);
		PreparedStatement pstmt;
		PrintWriter pw=resp.getWriter();
		ResultSet rs;
		String query="select account_balance from transactioin_data where account_number=?";
		String query1="update transactioin_data set account_balance=? where account_number=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setDouble(1, number);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				double accountBalance=rs.getDouble(1);
				pstmt=con.prepareStatement(query1);
				if(amt>0)
				{
				pstmt.setDouble(1, amt+accountBalance);
				pstmt.setInt(2, number);
				int count=pstmt.executeUpdate();
				pw.print("<h1> rs "+amt+"<h1>Amount Deposited Successfully</h1>");
				}
				else
				{
					pw.print("<h1>"+"Invalid amount</h1>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
