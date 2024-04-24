//package business.logic;
//
//
//
//import Entities.Backend.EmployeeAddress;
//import Entities.Backend.EmployeeDetails;
//import backend.exceptions.UserAlreadyExistsException;
//import backend.exceptions.ValidationException;
//import implementations.App;
//import interfaces.EmployeeInterface;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.sql.*;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
////annotation tells JUnit to use the Mockito test runner to execute the tests in this class.
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest2 {
//    //@Mock: This annotation is used to create mock objects for the dependencies of the class being tested.
//    @Mock
//    private Connection mockConnection;
//
//    @Mock
//    private PreparedStatement mockPreparedStatement;
//
//    @Mock
//    private ResultSet mockResultSet;
//
//    //mocks the object dependencies of the class being tetsed and injected automatically...need to have manual constructor or getter or setter
//    @InjectMocks
//    private implementations.App repository;
//
////    @Before
////    public void setUp() {
////        MockitoAnnotations.initMocks(this);
////    }
//
//    @Test
//    public void testWriteEmployeeDetails() throws SQLException {
//        EmployeeDetails employeeDetails = new EmployeeDetails(
//                "Prashant", "D", "Karkala", 7898765678L, 103,
//                new EmployeeAddress("pantarapalya", "karnataka", "india", 589087),
//                new EmployeeAddress("katriguppe", "karnataka", "india", 786567)
//        );
//
//        // Mocking database interaction
//        //executeUpdate() returns integer value which represent number of rows affected by the query else returns 0.
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        assertTrue(repository.writeEmployeeDetails(employeeDetails));//Asserts that a condition is true. If it isn't it throws an AssertionError without a message.
//    }
//
//    @Test(expected = ValidationException.class)
//    public void testWriteDuplicateEmployeeDetails() throws SQLException {
//        EmployeeDetails employeeDetails = new EmployeeDetails(
//                "Prashant", "D", "Karkala", 7898765678L, 103,
//                new EmployeeAddress("pantarapalya", "karnataka", "india", 589087),
//                new EmployeeAddress("katriguppe", "karnataka", "india", 786567)
//        );
//
//        // Mocking database interaction
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//
//        //expected to throw an exception because user already exists
//        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLIntegrityConstraintViolationException());
//
//        repository.writeEmployeeDetails(employeeDetails); // This should throw UserAlreadyExistsException
//    }
//
//    @Test
//    public void testEmployeeOutputDetails() throws SQLException {
//        // Mocking database interaction
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        //The next method is typically used to iterate over the rows returned by a database query.
//        when(mockResultSet.next()).thenReturn(true,false);//assumed only one entry so true next false that is to say that no more contents
//
//        List<EmployeeDetails> employeeList = repository.employeeOutputDetails();
////        System.out.println(employeeList.size());
//       // System.out.println(employeeList.toString());
//        assertNotNull(employeeList);
//        //assertFalse(employeeList.isEmpty());
//    }
//
//    @Test
//    public void testFindEmployeesByPincode() throws SQLException {
//
//        int pincode = 12345;
//
//        // Mocking database interaction
//        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true, false);
//        when(mockResultSet.getInt(any(String.class))).thenReturn(pincode);
//
//        List<EmployeeDetails> employees = repository.findEmployeesByPincode(pincode);
//        assertNotNull(employees);
//        assertTrue(employees.isEmpty());
////        Optional<EmployeeDetails> firstEmployee = employees.stream().findFirst();
////        assertTrue(firstEmployee.isPresent());
////        assertEquals(pincode, firstEmployee.get().getTempAddress().getPincode());
////        assertEquals(pincode, firstEmployee.get().getPermAddress().getPincode());
//    }
//}
//
