package servlet.tests;

import Tasks.CreateAccount;
import Tasks.FindByTransacName;
import Tasks.FindByUsername;
import com.google.gson.Gson;
import org.example.Transaction;
import org.example.User;
import org.example.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.example.UserServices;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Endpoint {
    @Mock
    private UserServices services;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException{
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }


    @Test
// Test the findbyusername() method
    public void testFindByUsername() throws ServletException, IOException {
        // Create an instance of FindByUsername
        FindByUsername findByUsername = new FindByUsername();
        findByUsername.userServices = services;

        // Create sample user objects
        User user1 = new User("raksha", "12345", "Bangalore", "raksha@gmail.com", 9878987890L, 12000.0);
        User user2 = new User("razi", "987621", "Udupi", "razi@gmail.com", 9876325643L, 32000.0);

        // Mock the behavior of request.getParameter("username") to return "raksha"
        when(request.getParameter("username")).thenReturn("raksha");

        // Mock the behavior of services.callFindById() to return user1 when "raksha" is provided
        when(services.callFindById(("raksha"))).thenReturn(user1);

        // Call the method under test (doGet) with the mocked request and response objects
        findByUsername.doGet(request, response);

        // Verify that the response content type is set to "application/json"
        verify(response).setContentType("application/json");

        // Verify that services.callFindById() was called with the correct parameter value ("raksha")
        verify(services).callFindById("raksha");
    }


    @Test
    //testing the findByTransacName() method
    public void testFindByTransacName() throws ServletException, IOException {
        FindByTransacName findByTransacName=new FindByTransacName();
        findByTransacName.userServices=services;


        Transaction transaction1=new Transaction("Mahesh","Friend",12000.0,new Date(12,2,2024));
        Transaction transaction2=new Transaction("Razi","Bills",15000.0,new Date(15,3,2024));
        Transaction transaction3=new Transaction("Raksha","Medicine",21000.0,new Date(2,3,2024));

        List<Transaction> users= Stream.of(transaction3,transaction2).collect(Collectors.toList());

        when(request.getParameter("transactionAmount")).thenReturn("12000");

        when(services.callFindByUsername("Mahesh")).thenReturn(users);

        findByTransacName.doGet(request,response);

        verify(response).setContentType("application/json");

        verify(services).callFindByUsername(anyString());
    }


    @Test
    //testing the createAccount() method
    public void testCreate() throws ServletException, IOException {
        CreateAccount createAccount=new CreateAccount();
        createAccount.init();
        createAccount.userServices=services;

        User user1=new User("raksha","12345","Bangalore","raksha@gmail.com",9878987890L,12000.0);
        User user2=new User("razi","987621","Udupi","razi@gmail.com",9876325643L,32000.0);

        //when(request.getReader()).then("{\"username\":\"raksha\",\"password\":12345,\"address\":\"Bangalore\",\"email\":\"raksha@gmail.com\",\"contact\":9878987890,\"balance\":12000.0}");
        Gson gson=new Gson();
        String jsonUser=gson.toJson(user1);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonUser)));
        //doNothing().when(services).callSave(user1);
        createAccount.doPost(request,response);
        verify(response).setContentType("application/json");
        verify(services).callSave(user2);
        verify(response).setStatus(HttpServletResponse.SC_OK);

    }
}
