package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/second/*")
public class SIPCalculator extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                //super.doGet(req, resp);
                //this method is used to calculate SIP taking the input as monthlyInvestment,expectedReturns,periodYears....implementation is similar to previous task
                String reqMonthlyInvestment=req.getParameter("monthlyInvestment");
                String reqExpectedReturns=req.getParameter("expectedReturns");
                String reqPeriodYears = req.getParameter("periodYears");

                String slabChoice=req.getParameter("slabs");
                String salaryAmount=req.getParameter("salary");

                Double principalAmount = Double.parseDouble(reqMonthlyInvestment)*12*Double.parseDouble(reqPeriodYears);
                Double periodicInRate=(Double.parseDouble(reqExpectedReturns)/100)/12;
                if(principalAmount!=null&&periodicInRate!=null){
                        Double futureValue = Double.parseDouble(reqMonthlyInvestment)*((Math.pow(1+periodicInRate,Double.parseDouble(reqPeriodYears)*12)-1) / periodicInRate)*(1+periodicInRate);
                        double estimatedReturns=futureValue-principalAmount;
                        System.out.println("Principle Amount "+principalAmount+"\n"+"Estimated Returns "+estimatedReturns+"\n"+"Total Interest "+futureValue);
                        resp.setContentType("application/json");
                        Gson gson=new Gson();
                        String message = gson.toJson(futureValue);
                        resp.getWriter().println(message);
                       resp.setStatus(HttpServletResponse.SC_OK);
                        resp.getWriter().println(estimatedReturns);
                }
                else{

                        String recieved = determineSlabs(slabChoice,Double.parseDouble(salaryAmount));
                        resp.getWriter().println(recieved);
                }
        }

        public String determineSlabs(String slabChoice, Double salaryAmount) {
                //based on the previous task of calculating tax on the basis of slabs as ne or old ....same is implemented here but using servlets
                switch (slabChoice.toLowerCase()) {
                        case "old"://calculation of tax based on old slab
                                if (salaryAmount <= 250000) {
                                        return "No tax to be paid";
                                } else if (salaryAmount > 250000 && salaryAmount <= 500000) {
                                       return ("tax to be paid is " + (salaryAmount * 0.05));
                                } else if (salaryAmount > 500000 && salaryAmount <= 750000) {
                                        return ("Tax to be paid is " + (salaryAmount * 0.2));
                                } else if (salaryAmount > 750000 && salaryAmount <= 1000000) {
                                      return ("Tax to be paid is " + (salaryAmount * 0.2));
                                } else if (salaryAmount > 1000000 && salaryAmount <= 1250000) {
                                        return ("Tax to be paid is " + salaryAmount * 0.3);
                                } else if (salaryAmount > 1250000 && salaryAmount <= 1500000) {
                                       return ("Tax to be paid is " + salaryAmount * 0.3);
                                } else {
                                        return ("Tax to be paid is " + salaryAmount * 0.3);
                                }
                                //break;
                        case "new"://calculation of tax based on new slab
                                if (salaryAmount <= 250000) {
                                        return ("No tax to be paid");
                                } else if (salaryAmount > 250000 && salaryAmount <= 500000) {
                                        return ("tax to be paid is " + (salaryAmount * 0.05));
                                } else if (salaryAmount > 500000 && salaryAmount <= 750000) {
                                        return ("Tax to be paid is " + salaryAmount * 0.2);
                                } else if (salaryAmount > 750000 && salaryAmount <= 1000000) {
                                       return ("Tax to be paid is " + salaryAmount * 0.2);
                                } else if (salaryAmount > 1000000 && salaryAmount <= 1250000) {
                                        return ("Tax to be paid is " + salaryAmount * 0.3);
                                } else if (salaryAmount > 1250000 && salaryAmount <= 1500000) {
                                        return ("Tax to be paid is " + salaryAmount * 0.3);
                                } else {
                                        return ("Tax to be paid is " + salaryAmount * 0.3);
                                }
                               // break;
                        default:return "Not applicable";
                }

        }
}