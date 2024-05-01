package com.example.backend.rest.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dao.entities.Accounts;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.exceptions.NoDataFoundException;
import com.project.dao.remotes.AccountRepository;
import com.project.dao.security.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import services.accounts.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.rmi.ServerException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //http://localhost:7001/backend-0.0.1-SNAPSHOT/v3/api-docs
    //http://localhost:8082/accounts/closeAccounts

    @Autowired
    private AccountRepository accountService;

    @Autowired
    private MyBankCustomersService myBankCustomersService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(AccountController.class);
//
//    @Operation(summary = "Closing the account service")
//    @PutMapping("/closeAccounts")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Account closed successfully"),
//            @ApiResponse(responseCode = "404", description = "Account or Customer Not found"),
//            @ApiResponse(responseCode = "403", description = "Customer is inactive"),
//            @ApiResponse(responseCode = "500", description = "Internal server error"),
//            @ApiResponse(responseCode = "400",description = "Bad Request"),
//            @ApiResponse(responseCode = "422",description = "Entered details does not match with data in DB")
//    })
//    public ResponseEntity<Object> closeAccountService(@Valid @RequestBody Accounts account) {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        MyBankCustomers customers=myBankCustomersService.findByUsername(username);
//        System.out.println(customers.getUsername());
//        System.out.println(customers.getCustomerId());
//        try {
//            account.setCustomerId(customers.getCustomerId());
//            System.out.println(account);
//            Accounts updatedAccount = accountService.UpdateAccountService(account);
//            logger.warn(resourceBundle.getString("account.close.service"));
//
//            Map<String, Object> responseMap = new HashMap<>();
//            responseMap.put("accountNumber", updatedAccount.getAccountNumber());
//            responseMap.put("accountType", updatedAccount.getAccountType());
//            responseMap.put("accountStatus", updatedAccount.getAccountStatus());
//            responseMap.put("accountBalance", updatedAccount.getAccountBalance());
//
//            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
//
//            //return ResponseEntity.ok(updatedAccount);
//        } catch (AccountNotFoundException accountException) {
//            logger.warn(resourceBundle.getString("no.active.accounts"));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountException.getMessage());
//        }catch ( CustomerNotFoundException customerException){
//            logger.warn(resourceBundle.getString("no.customer.id"));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerException.getMessage());
//        }catch (ServerException  | DataAccessException serverException) {
//            logger.warn(resourceBundle.getString("no.data"));
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serverException.getMessage());
//        }catch (NoDataFoundException dataException){
//            logger.warn(resourceBundle.getString("data.error"));
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(dataException.getMessage());
//        }
//    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    @Operation(summary = "Closing the account service")
    @PutMapping("/closeAccounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account closed successfully"),
            @ApiResponse(responseCode = "404", description = "Account or Customer Not found"),
            @ApiResponse(responseCode = "403", description = "Customer is inactive"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "422",description = "Entered details does not match with data in DB")
    })
    public ResponseEntity<Object> closeAccountService(@Valid @RequestBody Accounts account) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        String username = authentication.getName();
        MyBankCustomers customers=myBankCustomersService.findByUsername(username);

//        int flag=0;
//        List<Accounts> customersList= (List<Accounts>) myBankCustomersService.findByAccountNumber(customers.getCustomerId());
//        for(Accounts each:customersList){
//            if(Objects.equals(account.getAccountNumber(),each.getAccountNumber())){
//                System.out.println(account.getAccountNumber());
//                System.out.println(each.getAccountNumber());
//                flag=1;
//                //return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account number does not match the authenticated user.");
//            }
//        }
//        if(flag==1){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account number does not match the authenticated user.");
//        }

//        // Find accounts associated with the authenticated customer's customer ID
//        List<Accounts> customerAccounts = myBankCustomersService.findByAccountNumber(customers.getCustomerId());
//        // Check if the provided account number belongs to the authenticated customer
//        boolean accountExists = customerAccounts.stream()
//                .anyMatch(acc -> Objects.equals(account.getAccountNumber(), acc.getAccountNumber()));
//
//        if (!accountExists) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account number does not match the authenticated user.");
//        }

        try {
            account.setCustomerId(customers.getCustomerId());
            System.out.println(account);
            Accounts updatedAccount = accountService.UpdateAccountService(account);
            logger.warn(resourceBundle.getString("account.close.service"));

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("accountNumber", updatedAccount.getAccountNumber());
            responseMap.put("accountType", updatedAccount.getAccountType());
            responseMap.put("accountStatus", updatedAccount.getAccountStatus());
            responseMap.put("accountBalance", updatedAccount.getAccountBalance());

            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
            //return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException accountException) {
            logger.warn(resourceBundle.getString("inactive.account"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountException.getMessage());
        }catch ( CustomerNotFoundException customerException){
            logger.warn(resourceBundle.getString("no.customer.id"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerException.getMessage());
        }catch (ServerException  | DataAccessException serverException) {
            logger.warn(resourceBundle.getString("no.data"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serverException.getMessage());
        }catch (NoDataFoundException dataException){
            logger.warn(resourceBundle.getString("data.error"));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(dataException.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}