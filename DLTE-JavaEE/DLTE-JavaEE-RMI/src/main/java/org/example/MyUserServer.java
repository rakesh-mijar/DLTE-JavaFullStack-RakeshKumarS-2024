package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyUserServer extends UnicastRemoteObject implements MyUserFunctions,Serializable {
    private static Context context;
    private Registry registry;

    private UserServices services;


    @Override
    public List<User> findAll() throws RemoteException {
        List<User> user =services.callFindAll();
        List<User> returned=new ArrayList<>();
        for (User each:user){
            returned.add(user.get(1));
        }
        return returned;
    }
    public MyUserServer() throws RemoteException {
        super();
        services=new UserServices(new DatabaseTarget());
        try {
            registry= LocateRegistry.createRegistry(3030);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws NamingException, RemoteException {
        MyUserServer myCardServer=new MyUserServer();
        context.bind("java:/user",myCardServer);
    }
}
