//package com.example.demo;
//
//import com.example.demo.dao.Transactions;
//import com.example.demo.dao.TransactionsService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private TransactionsService transactionService;
//
//    //testing the method to create a new transaction entry
//    @Test
//    void testSave() {
//        Transactions transaction1 = new Transactions(12323456L, new Date("12/2/2024"), "Ramesh", "Suresh", 11000.0, "Friend");
//        Transactions transaction2 = new Transactions(765423436L, new Date("11/8/2024"), "Venkatesh", "Prajwal", 32000.0, "Health");
//        Transactions transaction3 = new Transactions(176543343L, new Date("24/9/2024"), "Razi", "Darshan", 25000.0, "Education");
//
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//
//        Transactions actual = transactionService.publishNewTransactions(transaction2);
//        //test case ----1 should pass because both actual and expected are similar
//        assertEquals(transaction2.getTransactionAmount(), actual.getTransactionAmount());
//
//        //System.out.println(transaction1.getTransactionAmount());
//        //System.out.println(actual.toString());
//
//        //test case---2 should failing because both actual and expected are different
//        //assertNotEquals(transaction1.getTransactionId(), actual.getTransactionId());
//    }
//
//    //testing the method to fetch the transaction details based on sender name
//    @Test
//    void testViewBySender() {
//        Transactions transaction1 = new Transactions(12323456L, new Date("12/2/2024"), "Ramesh", "Suresh", 11000.0, "Friend");
//        Transactions transaction2 = new Transactions(765423436L, new Date("11/8/2024"), "Venkatesh", "Prajwal", 32000.0, "Health");
//        Transactions transaction3 = new Transactions(176543343L, new Date("24/9/2024"), "Razi", "Darshan", 25000.0, "Education");
//
//        List<Transactions> expectedList = Stream.of(transaction2, transaction3).collect(Collectors.toList());
//
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedList);
//
//        List<Transactions> actual = transactionService.findBySender("Venkatesh");//apiFindBySender("Venkatesh");
//
//        //test case passed because the actual sender is present in the expectedlist
//        assertEquals(expectedList, actual);
//
//        //test case failed because the actual sender is not present in the expectedlist
//        //assertNotEquals(expectedList, actual);
//    }
//
//    //testing the method to fetch the transaction details based on receiver name
//    @Test
//    void testViewByReceiver() {
//        Transactions transaction1 = new Transactions(12323456L, new Date("12/2/2024"), "Ramesh", "Suresh", 11000.0, "Friend");
//        Transactions transaction2 = new Transactions(765423436L, new Date("11/8/2024"), "Venkatesh", "Prajwal", 32000.0, "Health");
//        Transactions transaction3 = new Transactions(176543343L, new Date("24/9/2024"), "Razi", "Darshan", 25000.0, "Education");
//
//        List<Transactions> expectedList = Stream.of(transaction1, transaction3).collect(Collectors.toList());
//
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedList);
//
//        List<Transactions> actual = transactionService.findByReceiver("Suresh");
//
//        //test case passed because the actual sender is present in the expectedlist
//        assertEquals(expectedList, actual);
//
//        //test case failed because the actual sender is not present in the expectedlist
//       // assertNotEquals(expectedList, actual);
//    }
//
//    //testing the method to fetch the transaction details based on transaction amount
//    @Test
//    void testViewByAmount() {
//        Transactions transaction1 = new Transactions(12323456L, new Date("12/2/2024"), "Ramesh", "Suresh", 11000.0, "Friend");
//        Transactions transaction2 = new Transactions(765423436L, new Date("11/8/2024"), "Venkatesh", "Prajwal", 32000.0, "Health");
//        Transactions transaction3 = new Transactions(176543343L, new Date("24/9/2024"), "Razi", "Darshan", 25000.0, "Education");
//
//        List<Transactions> expectedList = Stream.of(transaction1, transaction3).collect(Collectors.toList());
//
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedList);
//
//        List<Transactions> actual = transactionService.findByAmount(25000.0);
//
//        //test case passed because the actual sender is present in the expectedlist
//        assertEquals(expectedList,actual);
//
//        //test case failed because the actual sender is not present in the expectedlist
//        assertNotEquals(expectedList, actual);
//    }
//
//    //testing the method to fetch the transaction details based on transaction remarks
//    @Test
//    void testUpdateTransactionRemarks() {
//        Transactions transaction1 = new Transactions(12323456L, new Date("12/2/2024"), "Ramesh", "Suresh", 11000.0, "Friend");
//        Transactions transaction2 = new Transactions(765423436L, new Date("11/8/2024"), "Venkatesh", "Prajwal", 32000.0, "Health");
//        Transactions transaction3 = new Transactions(176543343L, new Date("24/9/2024"), "Razi", "Darshan", 25000.0, "Education");
//
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//
//        Transactions actual = transactionService.updateRemarks(transaction2);
//        //test case ----1 should pass because both actual and expected are similar
//        assertEquals(transaction2.getTransactionAmount(), actual.getTransactionAmount());
//
//        //System.out.println(transaction1.getTransactionAmount());
//        //System.out.println(actual.toString());
//
//        //test case---2 should failing because both actual and expected are different
//        //assertNotEquals(transaction1.getTransactionId(), actual.getTransactionId());
//    }
//
//
//
//}
