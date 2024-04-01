//package business.logic;
//
//import Entities.Backend.EmployeeAddress;
//import Entities.Backend.EmployeeDetails;
//import backend.exceptions.UserAlreadyExistsException;
//import implementations.App;
//import interfaces.EmployeeInterface;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//public class AppTest {
//
//    private interfaces.EmployeeInterface repository;
//
//    //this need not be static and executes only once before class execution
//    @Before
//    public void initialize() throws SQLException {
//        repository = new implementations.App();
//    }
//
////    //this should be static and executes before every class
////    @BeforeClass
////    public static void initialize() throws SQLException {
////        repository = new implementations.App();
////    }
//
//    //testing writing into the database here timeout defines the time of execution specify changes
//    @Test//(timeout = 10)
//    public void testWriteEmployeeDetails() throws SQLException {
//        EmployeeDetails employeeDetails = new EmployeeDetails(
//                "Sharath", "H", "Kunjar", 9087678900L, 200,
//                new EmployeeAddress("1-99 Radha nilaya", "Karnataka", "India", 786545),
//                new EmployeeAddress("4-56 Radha nilaya", "Karnataka", "India", 345678)
//        );
//        assertTrue(repository.writeEmployeeDetails(employeeDetails));
//    }
//
//    //when already existing employee is given we expect a exception thrown
//    @Test(expected = UserAlreadyExistsException.class)
//    public void testWriteDuplicateEmployeeDetails() throws SQLException {
//        EmployeeDetails employeeDetails = new EmployeeDetails(
//                "Sharath", "H", "Kunjar", 9087678900L, 200,
//                new EmployeeAddress("1-99 Radha nilaya", "Karnataka", "India", 786545),
//                new EmployeeAddress("4-56 Radha nilaya", "Karnataka", "India", 345678)
//        );
//        repository.writeEmployeeDetails(employeeDetails); // This should throw UserAlreadyExistsException
//    }
//
//    //testing the method that displays the details of employee
//    @Test
//    public void testEmployeeOutputDetails() throws SQLException {
//        List<EmployeeDetails> employeeList = repository.employeeOutputDetails();
//        //assertNotNull(employeeList);  //test case passed
//        assertNull(employeeList);   //test case failed
//        //assertFalse(employeeList.isEmpty());   //test case passed
//    }
//
//    //testing the method that displays the details of employee
//    @Test
//    public void testFindEmployeesByPincode() throws SQLException {
//        int pincode = 123453;
//        List<EmployeeDetails> employees = repository.findEmployeesByPincode(pincode);
//
//        assertFalse(employees.isEmpty()); //test failed because there is no employee with such pincode
//        //assertTrue(employees.isEmpty()); //test passed beacuse there is no employee with such pincode
//    }
//
//    @Test
//    public void testFindEmployeesByPincode2() throws SQLException{
//        int pincode=574225;
//        List<EmployeeDetails> employees =repository.findEmployeesByPincode(pincode);
//
//
//        //Optional is a container object which may or may not contain a non-null value. if null returns corrsponding msg else if contains any data the first employee details is returned
//        Optional<EmployeeDetails> firstEmployee = employees.stream().findFirst();
//
//
//        //assertTrue(firstEmployee.isPresent());
//
//        //System.out.println(firstEmployee.get().getFirstName());-----just to check who is employee
//
//        //test case failed because the given pincode is not similar to the temporary address pincode
//        assertEquals(pincode, firstEmployee.get().getTempAddress().getPincode());
//
//        //test case passed because the given pincode is similar to the permanent address pincode
//        //assertEquals(pincode, firstEmployee.get().getPermAddress().getPincode());
//    }
//}
