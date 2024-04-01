package org.backend.services;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

//public class SoapEndpoints {
//    private static String url = "http://localhost:1000/soap/dao";
//    public static void main(String[] args) throws SQLException {
//        EmployeeSoap employeeSoap = new EmployeeSoap();
//        System.out.println("Webservice hosted @ "+url);
//        Endpoint.publish(url, employeeSoap);
//    }
//}

public class SoapEndpoints {
    private static String url = "http://localhost:1000/soap/dao";

    public static void main(String[] args) {
        try {
            EmployeeSoap employeeSoap = new EmployeeSoap();
            System.out.println("Webservice hosted @ " + url);
            Endpoint.publish(url, employeeSoap);
        } catch (Exception e) {
            System.err.println("Error occurred while publishing the SOAP endpoint: " + e.getMessage());
            e.printStackTrace();
        }
    }
}