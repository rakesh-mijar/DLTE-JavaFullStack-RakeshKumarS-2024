package com.example.backend.rest.services;

import com.project.dao.entities.Accounts;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.exceptions.NoDataFoundException;
import com.project.dao.remotes.AccountRepository;
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
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.ResourceBundle;
@RestController
@RequestMapping("/accounts")
public class AccountController {

    //http://localhost:7001/backend-0.0.1-SNAPSHOT/v3/api-docs
    //http://localhost:8082/accounts/closeAccounts

    @Autowired
    private AccountRepository accountService;

    @Autowired
    private MyBankCustomersService myBankCustomersService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(AccountController.class);

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // method to fetch the owner's username from the account object
        String accountOwnerUsername = myBankCustomersService.getAccountOwnerUsername(account.getAccountId());

        // Check if the authenticated user matches the owner of the account
        if (!username.equals(accountOwnerUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resourceBundle.getString("access.denied"));
        }

        try {
            Accounts updatedAccount = accountService.UpdateAccountService(account);
            logger.warn(resourceBundle.getString("account.close.service"));
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException accountException) {
            logger.warn(resourceBundle.getString("no.active.accounts"));
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