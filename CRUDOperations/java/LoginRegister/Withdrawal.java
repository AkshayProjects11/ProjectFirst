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
@WebServlet("/wdrawlink")
public class Withdrawal extends HttpServlet{
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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt=req.getParameter("withdraw");
		String accountNumber=req.getParameter("number");
		double amt1=Double.parseDouble(amt);
		int number=Integer.parseInt(accountNumber);
		PreparedStatement pstmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		String query="select account_balance from transactioin_data where account_number=?";
		String query1="update transactioin_data set account_balance=? where account_number=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setDouble(1, number);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				double accountBalance=rs.getDouble(1);
				if(amt1<accountBalance)
				{
				pstmt=con.prepareStatement(query1);
				pstmt.setDouble(1, accountBalance-amt1);
				pstmt.setDouble(2, number);
				int count=pstmt.executeUpdate();
				pw.print("<h1>"+amt1+"Withdrawal Successfully");
				}
				else
				{
					pw.print("<h1>"+"Insufficient accountBalance</h1>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
