package com.example.backend.rest.services;

import com.project.dao.entities.Accounts;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private AccountRepository accountService;


    @PutMapping("/closeAccounts")
    public ResponseEntity<Object> closeAccountService(@Valid @RequestBody Accounts account) {
        try {
            Accounts updatedAccount = accountService.UpdateAccountService(account);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException accountException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountException.getMessage());
        }catch ( CustomerNotFoundException customerException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerException.getMessage());
    }catch (ServerException  |MethodArgumentTypeMismatchException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
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