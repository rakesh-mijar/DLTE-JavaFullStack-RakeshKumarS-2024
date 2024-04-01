package second;


        import com.google.gson.Gson;

        import javax.servlet.GenericServlet;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.Date;
        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

@WebServlet("/second/obj2/")
public class NewTransactionAnalysis extends GenericServlet {
    //Array of objects corresponding to the transaction entity

    private List<Transaction> myTransactions;

    @Override
    public void init() throws ServletException {
        myTransactions = Stream.of(
                new Transaction(new Date("1/20/2024"), 1234.0, "Mahesh", "Education"),
                new Transaction(new Date("11/2/2023"), 123455.0, "Pranava", "Friend"),
                new Transaction(new Date("10/1/2024"), 45678.0, "Jany", "Health")
        ).collect(Collectors.toList());
    }

//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//
//        //get method to receive minimum and maximum transaction as the parameters
//        String minAmount = request.getParameter("min");
//        String maxAmount = request.getParameter("max");
//
//        //if the values are not null then filter based on the range
//        if (minAmount != null && maxAmount != null) {
//            double minimumAmount = Double.parseDouble(minAmount);
//            double maximumAmount = Double.parseDouble(maxAmount);
//
//            //lambda expression to filter based on the range given.
//            List<Transaction> transactions = myTransactions.stream()
//                    .filter(each -> each.getTransactionAmount() >= minimumAmount && each.getTransactionAmount() <= maximumAmount)
//                    .collect(Collectors.toList());
//
//            Gson gson = new Gson();
//            response.setContentType("application/xml");
//            String jsonTransaction = gson.toJson(transactions);
//            //response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().println(jsonTransaction);
//        } else {
//            //Read All: GET service
//            Gson gson = new Gson();
//            response.setContentType("application/xml");
//            String json = gson.toJson(myTransactions);
//            //response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().println(json);
//        }
//    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // Get method to receive minimum and maximum transaction as the parameters
        String minAmount = request.getParameter("min");
        String maxAmount = request.getParameter("max");

        // Initialize HTML content
        StringBuilder htmlContent = new StringBuilder("<html><head><title>Transaction Analysis</title></head><body>");

        // If the values are not null, filter based on the range
        if (minAmount != null && maxAmount != null) {
            double minimumAmount = Double.parseDouble(minAmount);
            double maximumAmount = Double.parseDouble(maxAmount);

            // Lambda expression to filter based on the range given.
            List<Transaction> transactions = myTransactions.stream()
                    .filter(each -> each.getTransactionAmount() >= minimumAmount && each.getTransactionAmount() <= maximumAmount)
                    .collect(Collectors.toList());

            // Append transaction details to HTML content
            htmlContent.append("<div><h1>Transactions within the range:</h1></div>");
            for (Transaction transaction : transactions) {
                htmlContent.append("<p>").append(transaction.toString()).append("</p>");
            }
        } else {
            // Read All: GET service
            htmlContent.append("<div><h1>All Transactions:</h1></div>");
            int i=1;
            for (Transaction transaction : myTransactions) {
                htmlContent.append("<div>");
                htmlContent.append("<h2>").append("<p>").append("Transaction "+i).append("</h2>").append("</p>");
                htmlContent.append("<p>").append("Reciept Name :"+transaction.getReciept()).append("</p>");
                htmlContent.append("<p>").append("Date of transaction :"+transaction.getDateOfTransaction()).append("</p>");
                htmlContent.append("<p>").append("Transaction Amount :"+transaction.getTransactionAmount()).append("</p>");
                htmlContent.append("<p>").append("Remarks :"+transaction.getRemarks()).append("</p>");
                htmlContent.append("</div>");
                i++;
            }
        }

        // Close HTML content
        htmlContent.append("</body></html>");

        // Set response content type to HTML
        response.setContentType("text/html");

        // Write HTML content to response
        response.getWriter().println(htmlContent.toString());
    }


    @Override
    public void destroy() {
        //clear all the resources when the current servlet is closed.
        myTransactions.clear();

    }
}
