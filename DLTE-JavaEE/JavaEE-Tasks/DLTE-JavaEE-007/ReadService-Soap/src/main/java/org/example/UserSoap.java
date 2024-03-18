package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class UserSoap {
    private UserServices services;
    public UserSoap(){
        StorageTarget storageTarget = new DatabaseTarget();
        services=new UserServices(storageTarget);
    }

    @WebMethod
    @WebResult(name="GroupOfUsers")
    public GroupOfTransactions readAllByUsername(@WebParam(name="String")String username){
        GroupOfTransactions groupOfTransactions = new GroupOfTransactions();
        List<Transaction> list = services.callFindByUsername(username);
        groupOfTransactions.setTransactions(list);
        return groupOfTransactions;
    }
}
