package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserSoap {
    private UserServices userServices;
    public UserSoap(){
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
    }

    //method to create the account, in terms of method it is callSave()
    @WebMethod
    public void createAccount(@WebParam(name="User")User user){
        userServices.callSave(user);
    }

    //method to read the contents of user table based on the username
    @WebMethod
    @WebResult(name="Users")
    public User readById(@WebParam(name="String")String username){
        return userServices.callFindById(username);
    }

    //method to read the contents from transaction table based on the username specified.
    @WebMethod
    @WebResult(name="TransactionGroup")
    public TransactionGroup readAllById(@WebParam(name="String")String username){
        TransactionGroup transactionGroup=new TransactionGroup();
        List<Transaction> transactions =userServices.callFindByUsername(username);
        transactionGroup.setTransactions(transactions);
        return transactionGroup;
    }

}
