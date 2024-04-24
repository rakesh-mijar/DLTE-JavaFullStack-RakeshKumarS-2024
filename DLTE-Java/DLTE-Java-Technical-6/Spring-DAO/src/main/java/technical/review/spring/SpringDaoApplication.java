package technical.review.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import technical.review.spring.entities.backend.EmployeeAddress;
import technical.review.spring.entities.backend.EmployeeDetails;
import technical.review.spring.services.EmployeeServices;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringDaoApplication {

    public static void main(String[] args) throws ServerException, SQLException {

        //SpringApplication.run(SpringDaoApplication.class, args);

        ConfigurableApplicationContext context= SpringApplication.run(SpringDaoApplication.class, args);
        EmployeeServices employeeServices=context.getBean(EmployeeServices.class);
        EmployeeDetails employee = new EmployeeDetails();

        employee.setFirstName("Rakesh");
        employee.setMiddleName("Kumar");
        employee.setLastName("Shetty");
        employee.setEmpId(124);
        employee.setEmpMobileNumber(9980342382L);


// Setting permanent address
        EmployeeAddress permanentAddress = new EmployeeAddress();
        permanentAddress.setStreetName("mijar");
        permanentAddress.setStateName("karnataka");
        permanentAddress.setCountryName("India");
        permanentAddress.setPincode(574225);
        employee.setPermAddress(permanentAddress);

// Setting temporary address
        EmployeeAddress temporaryAddress = new EmployeeAddress();
        temporaryAddress.setStreetName("thodar");
        temporaryAddress.setStateName("karnataka");
        temporaryAddress.setCountryName("India");
        temporaryAddress.setPincode(574227);
        employee.setTempAddress(temporaryAddress);

        //System.out.println(employeeServices.writeEmployeeDetails(employee));

       // List<EmployeeDetails> employeeDetailsList = employeeServices.findEmployeesById(121);
        //employeeDetailsList.forEach(System.out::println);

    }


//        AccountsServices debitCardServices=context.getBean(AccountsServices.class);
//        System.out.println(debitCardServices.UpdateAccountService(103L));
    }


