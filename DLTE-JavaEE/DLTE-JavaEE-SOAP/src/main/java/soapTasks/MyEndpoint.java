package soapTasks;

import javax.xml.ws.Endpoint;

public class MyEndpoint {
    private static String url="http://localhost:1000/rakesh";
    public static void main(String[] args) {
        MySource mySource=new MySource();
        System.out.println("SOAP webservice running "+url);
        Endpoint.publish(url,mySource);
    }
}
