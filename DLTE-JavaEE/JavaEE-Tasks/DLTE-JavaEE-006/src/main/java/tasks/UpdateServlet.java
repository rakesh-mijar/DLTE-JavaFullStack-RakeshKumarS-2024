package tasks;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/update/data")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Context is an interface with lookup methods, InitialContext is a method implemented from Context
            Context context = new InitialContext();
            Connection connection = null;
            //java:/OracleDS is the datasource created in JNDIA
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            connection = dataSource.getConnection();
            //parameters are given which is to be updated and based on which conditio through update query
            String requestUsername = req.getParameter("username");
            String requestContact = req.getParameter("contact");
            if (requestUsername != null && requestContact != null) {
                Long contact = Long.parseLong(requestContact);
                String query = "Update mybank_users set contact=? where username=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, contact);
                preparedStatement.setString(2, requestUsername);
                int result = preparedStatement.executeUpdate();
                if (result != 0)
                    resp.getWriter().println("User info updated");
                else
                    resp.getWriter().println("Updation failed");

            }
            connection.close();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

    }
}