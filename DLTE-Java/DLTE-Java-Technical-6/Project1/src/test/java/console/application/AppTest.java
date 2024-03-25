//package console.application;
//
//import Entities.Backend.EmployeeAddress;
//import Entities.Backend.EmployeeDetails;
//import interfaces.EmployeeInterface;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.*;
//
///**
// * Unit test for simple App.
// */
//public class AppTest
//{
//    static String testFilePath="test-credit.doc";
//    static List<EmployeeDetails> employeeDetails;
//    static interfaces.EmployeeInterface repository;
//    //implementations.App repository=new implementations.App();
//    public AppTest() throws SQLException {
//    }
//
//    @BeforeClass
//    public static void initialize() throws SQLException {
//        employeeDetails = Stream.of(
//                new EmployeeDetails("Mahima","M","Kottary",98765465678L,111,new EmployeeAddress("rr nagara","karnataka","inida",573552),new EmployeeAddress("jp nagara","karnataka","india",574567)),
//                new EmployeeDetails("Manasa","J","Salian",2345678765L,112,new EmployeeAddress("bt layout","karnataka","india",5742225),new EmployeeAddress("kr market","karnataka","india",576453)),
//                new EmployeeDetails("Rahul","N","Naidu",8765432456L,113,new EmployeeAddress("vidyagiri","karnataka","india",564356),new EmployeeAddress("Mailsandra","karnataka","india",567898))
//        ).collect(Collectors.toList());
//        repository=new implementations.App();
//    }
//    @Test
//    public void testFindByPincode() throws SQLException {
//        assertSame(0,repository.findEmployeesByPincode(573552).size());
//        //assertNotEquals(Optional.of(111),Optional.ofNullable(repository.findEmployeesByPincode(573552).get));
//    }
//
//    @Test
//    public void testSave() throws SQLException {
//        EmployeeAddress tempAddress=new EmployeeAddress("rr nagara","karnataka","india",573552);
//        EmployeeAddress permAddress=new EmployeeAddress("jp nagara","karnataka","india",574567);
//        EmployeeDetails employeeDetails=new EmployeeDetails("Mahima","M","Kottary",98765465678L,111,tempAddress,permAddress);
//        repository.writeEmployeeDetails(employeeDetails);
//       // implementations.App app1 = new implementations.App();
//        //app1.writeEmployeeDetails(employeeDetails);
//assertTrue(repository.findEmployeesByPincode(573552).get(1).getEmpId().equals(111));
//    }
//
//
//}
//
//
