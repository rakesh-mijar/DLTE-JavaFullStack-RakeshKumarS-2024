package task;


import org.apache.http.HttpEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.ContentType;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;

        import java.io.IOException;

public class RestToSoap {

    public static void main(String[] args) {
        String soapEndpointUrl = "http://localhost:1000/soap/dao/UserSoap"; // SOAP Service endpoint URL

        // XML SOAP Request
        String xmlRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ser=\"http://example.com/soap/service\">" +
                "<soapenv:Header/><soapenv:Body><ser:YourSOAPRequest/></soapenv:Body></soapenv:Envelope>";

        // Create HTTP POST request with XML SOAP payload
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(soapEndpointUrl);
            StringEntity requestEntity = new StringEntity(xmlRequest, ContentType.TEXT_XML);
            httpPost.setEntity(requestEntity);

            // Execute the request and process the response
            httpClient.execute(httpPost, response -> {
                HttpEntity responseEntity = response.getEntity();
                // Handle response here, e.g., parse XML SOAP response
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
