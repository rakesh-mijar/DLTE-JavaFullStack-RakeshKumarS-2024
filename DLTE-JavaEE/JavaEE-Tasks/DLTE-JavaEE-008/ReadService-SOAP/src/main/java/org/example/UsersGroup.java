package org.example;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;


public class UsersGroup {
    //list of users as an attribute with its getter, setter and toString() methods
    List<User> users;

    public UsersGroup() {
    }

    public UsersGroup(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersGroup{" +
                "users=" + users +
                '}';
    }
}
