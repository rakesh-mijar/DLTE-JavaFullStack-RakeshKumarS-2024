package org.example;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyUserFunctions extends Remote {
    List<User> findAll() throws RemoteException;
}
