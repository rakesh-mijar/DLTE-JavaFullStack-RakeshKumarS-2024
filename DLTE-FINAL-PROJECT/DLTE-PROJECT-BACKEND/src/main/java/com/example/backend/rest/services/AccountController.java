package com.example.backend.rest.services;


import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.remotes.AccountRepository;
import com.project.dao.remotes.CustomerRepository;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.rmi.ServerException;
import java.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //http://localhost:7001/backend-0.0.1-SNAPSHOT/v3/api-docs
    //http://localhost:8082/accounts/closeAccounts

    @Autowired
    private AccountRepository accountService;

    @Autowired
    private CustomerRepository myBankCustomersService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(AccountController.class);


    @Operation(summary = "Closing the account service")
    @PutMapping("/closeAccounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account closed successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "EXC001",description = "Account is already inactive")
    })
    public ResponseEntity<Object> closeAccountService(@Valid @RequestBody Accounts account) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        String username = authentication.getName();
        MyBankCustomers customers=myBankCustomersService.findByUsername(username);

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

            return ResponseEntity.status(HttpServletResponse.SC_OK).body(responseMap+resourceBundle.getString("account.close.service"));
        }
            catch (AccountNotFoundException accountException) {
                logger.warn(resourceBundle.getString("inactive.account"));
                return ResponseEntity.status(HttpServletResponse.SC_OK).body(resourceBundle.getString("account.error.one")+accountException.getMessage());

            }
        catch (ServerException serverException) {
            logger.warn(resourceBundle.getString("no.data"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serverException.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.OK)
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