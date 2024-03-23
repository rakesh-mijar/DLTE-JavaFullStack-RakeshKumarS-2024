package com.example.demojdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DemoJdbcApplicationTests {

   @Mock
    private JdbcTemplate jdbcTemplate;

   @InjectMocks
    private TransactionService transactionService;

   @Test
    void testSave()
   {
       TransactionNew transaction1=new TransactionNew(12323456L,new Date("12/2/2024"),"Ramesh","Suresh",11000.0,"Friend");
       TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
       TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

       lenient().when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);

       TransactionNew actual=transactionService.apiSave(transaction2);
       assertEquals(transaction1.getTransactionAmount(),actual.getTransactionAmount());//test case should pass because both actual and expected are similar

       assertNotEquals(transaction1.getTransactionId(),actual.getTransactionId());////test case should failing because both actual and expected are different;
   }

   @Test
    void testViewBySender(){
       TransactionNew transaction1=new TransactionNew(12323456L,new Date("12/2/2024"),"Ramesh","Suresh",11000.0,"Friend");
       TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
       TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

       List<TransactionNew> expectedList= Stream.of(transaction2,transaction3).collect(Collectors.toList());

       when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(expectedList);

       List<TransactionNew> actual=transactionService.apiFindBySender("Venkatesh");

       assertEquals(expectedList,actual);//test case passed because the actual sender is present in the expectedlist

       assertNotEquals(expectedList,actual);//test case failed because the actual sender is not present in the expectedlist
   }

    @Test
    void testViewByReceiver(){
        TransactionNew transaction1=new TransactionNew(12323456L,new Date("12/2/2024"),"Ramesh","Suresh",11000.0,"Friend");
        TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
        TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

        List<TransactionNew> expectedList= Stream.of(transaction1,transaction3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(expectedList);

        List<TransactionNew> actual=transactionService.apiFindByReciever("Ramesh");

        assertEquals(expectedList,actual);//test case passed because the actual sender is present in the expectedlist

        assertNotEquals(expectedList,actual);//test case failed because the actual sender is not present in the expectedlist
    }

    @Test
    void testViewByAmount(){
        TransactionNew transaction1=new TransactionNew(12323456L,new Date("12/2/2024"),"Ramesh","Suresh",11000.0,"Friend");
        TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
        TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

        List<TransactionNew> expectedList= Stream.of(transaction1,transaction3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(expectedList);

        List<TransactionNew> actual=transactionService.apiFindByAmount(25000.0);

       // assertEquals(expectedList,actual);//test case passed because the actual sender is present in the expectedlist

        assertNotEquals(expectedList,actual);//test case failed because the actual sender is not present in the expectedlist
    }


}
