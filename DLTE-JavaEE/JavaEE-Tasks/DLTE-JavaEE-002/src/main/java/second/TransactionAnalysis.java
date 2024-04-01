package second;

import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/second/obj/")
public class TransactionAnalysis extends HttpServlet {
    //Array of objects corresponding to the transaction entity

    private List<Transaction> myTransactions;
    @Override
    public void init() throws ServletException {
        myTransactions= Stream.of(new Transaction(new Date("1/20/2024"),1234.0,"Mahesh","Education"),
                new Transaction(new Date("11/2/2023"),123455.0,"Pranava","Friend"),
                new Transaction(new Date("10/1/2024"),45678.0,"Jany","Health")).collect(Collectors.toList());
        System.out.println("Hii");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // init();
        //get method to recieve minimum and maximum transactioin as the parameters
        String minAmount = req.getParameter("min");
        String maxAmount = req.getParameter("max");

        //if the values are not null then filter based on the range
        if(minAmount!=null && maxAmount!=null){
            double minimumAmount =Double.parseDouble(minAmount);
            double maximumAmount =Double.parseDouble(maxAmount);

            //lambda expression to filter based on the range given.
            List<Transaction> transactions = myTransactions.stream().filter(each->each.getTransactionAmount()>=minimumAmount && each.getTransactionAmount()<=maximumAmount).collect(Collectors.toList());

            Gson gson=new Gson();
            resp.setContentType("application/json");
            String jsonTransaction=gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(jsonTransaction);
        }
        else{

            //Read All: GET service
            Gson gson=new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(myTransactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        //Create any Transactions but it is temporary: POST service
       Gson gson=new Gson();
       Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
       myTransactions.add(transaction);
       resp.setStatus(HttpServletResponse.SC_OK);
       resp.getWriter().println(transaction.getReciept()+" has added to the records");
    }

    @Override
    public void destroy() {
        //clear all the resources when the current servlet is closed.
        myTransactions.clear();
        System.out.println("Ended ");
    }
}


