import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        try {
            // Load the JDBC driver
            Class.forName(driver);

            // Establish a connection
            Connection con = DriverManager.getConnection(url, "SYSTEM", "pritam");

            // Prepare SQL statement to update account balance
            String updateQuery = "update account set balance=balance+? WHERE num=?";
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setInt(1, amount);
            ps.setInt(2, num);

            // Execute the update statement
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                out.println("<h2>Funds deposited successfully</h2>");
                RequestDispatcher rd = request.getRequestDispatcher("Success.html");
                rd.include(request, response);
            } else {
                out.println("<h2>Failed to deposit funds. Account not found.</h2>");
            }

            // Close resources
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println("<h2>Exception: " + e.getMessage() + "</h2>");
        }
	}

}