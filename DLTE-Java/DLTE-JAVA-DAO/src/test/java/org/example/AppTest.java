package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private UserDatabaseRepository mockUserRepository;
    private UserServices services;

    @Before
    public void prepareStore() {
        when(mockStorageTarget.getUserRepository()).thenReturn(mockUserRepository);
        services = new UserServices(mockStorageTarget);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSave() {
        User user = new User("ramesh", "ram@123", "saagara", "ram@gmail.com", 2345630L, 25000.0);
        //User user1 = new User("pranava", "prana@123", "maala", "prana@gmail.com", 23456230L, 25000.0);

        doNothing().when(mockUserRepository).save(user);

        services.callSave(user);
        verify(mockUserRepository).save(user);
    }

    //testing find by id method
    @Test
    public void testFindById() {
        String username = "razi";
        String username1 = "muzain";

        User user = new User("razi", "razi@123", "udupi", "razi@gmail.com", 23445230L, 25890.0);
        User user1 = new User("muzain", "muzain@123", "karkala", "muzain@gmail.com", 7894561230L, 9000.0);

        when(mockUserRepository.findById(username1)).thenReturn(user1);

        User realUser = services.callFindById(username1);

        assertNotSame(user.getUsername(), realUser.getUsername());//test passed because the expected and actual username are not same

        assertEquals(user1.getUsername(), realUser.getUsername());//test passed because the expected and actual username are same...i.e muzain

        assertNotSame(user1.getUsername(), realUser.getUsername());//test failed because actually they are same but asserting as not true
    }
//test for balance
    @Test
    public void testBalance() {
        Double balance1 = 1000.0;
        Double balance2 = 52000.0;
        //User user1 = new User("pranava", "prana@123", "maala", "prana@gmail.com", 23456230L, 25000.0);
        User user1 = new User("sanath", "Ss@12345", "begar", "sanath@gmail.com", 7894561230L, 1000.0);

        when(mockUserRepository.findById("sanath")).thenReturn(user1);

        User realUser = services.callFindById("sanath");
        assertNotEquals(user1.getBalance(), realUser.getBalance()); //test failed fails because the actual and given balance are not same

    }
}

