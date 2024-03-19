package org.example;

import javax.xml.ws.Endpoint;

public class SoapEndpoints {

    private static String url = "http://localhost:1000/soap/dao";

    public static void main(String[] args) {
        UserSoap userSoap = new UserSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url, userSoap);
    }
}
