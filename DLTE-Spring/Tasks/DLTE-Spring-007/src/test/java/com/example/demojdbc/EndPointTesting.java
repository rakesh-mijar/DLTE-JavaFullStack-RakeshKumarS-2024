package com.example.demojdbc;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndPointTesting {
    @MockBean
    private TransactionService transactionService;
    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;

    //Testing the save method
    @Test
    void testSave() throws Exception {

        String request="{\n" +
                "        \"transactionId\": 1272345,\n" +
                "        \"transactionDate\": \"2024-10-20\",\n" +
                "        \"transactionBy\": \"Venki\",\n" +
                "        \"transactionTo\": \"Manoj\",\n" +
                "        \"transactionAmount\": 50000.0,\n" +
                "        \"transactionRemarks\": \"Friend\"\n" +
                "    }";

        TransactionNew transaction=new TransactionNew(1272345L,new Date("10/20/2024"),"Venki","Manoj",50000.0,"Friend");
        when(transactionService.apiSave(any())).thenReturn(transaction);

        //test case passed because the actual and expected entries are similar
//        mockMvc.perform(post("/transaction/jdbc/new/transac").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(1272345L)).
//                andExpect(jsonPath("$.transactionDate").value("2024-10-19T18:30:00.000+00:00")).
//                andExpect(jsonPath("$.transactionBy").value("Venki")).
//                andExpect(jsonPath("$.transactionTo").value("Manoj")).
//                andExpect(jsonPath("$.transactionAmount").value(50000.0)).
//                andExpect(jsonPath("$.transactionRemarks").value("Friend"));


        //test case failed because the expected and actual values are different
        mockMvc.perform(post("/transaction/jdbc/new/transac").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(23455L)).
                andExpect(jsonPath("$.transactionDate").value("2024-10-19T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionBy").value("Venki")).
                andExpect(jsonPath("$.transactionTo").value("Manoj")).
                andExpect(jsonPath("$.transactionAmount").value(50000.0)).
                andExpect(jsonPath("$.transactionRemarks").value("Friend"));
    }

    //testing the findbysender method
    @Test
    void testFindBySender() throws Exception {
        TransactionNew transaction1=new TransactionNew(12323456L,new Date("10/20/2024"),"Ramesh","Suresh",11000.0,"Friend");
        TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
        TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

        List<TransactionNew> expectedList= Stream.of(transaction1,transaction3).collect(Collectors.toList());

        when(transactionService.apiFindBySender("Ramesh")).thenReturn(expectedList);

        //test case passed because the actual and expected entries are similar
//        mockMvc.perform(get("/transaction/jdbc/sender/Ramesh")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(12323456L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].transactionBy").value("Ramesh")).
//                andExpect(jsonPath("$[0].transactionTo").value("Suresh")).
//                andExpect(jsonPath("$[0].transactionAmount").value(11000.0)).
//                andExpect(jsonPath("$[0].transactionRemarks").value("Friend"));

        //test case failed because the actual and expected entries are different
        mockMvc.perform(get("/transaction/jdbc/sender/Razi")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[1].transactionId").value(12323456L)).
                andExpect(jsonPath("$[1].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
                andExpect(jsonPath("$[1].transactionBy").value("Ramesh")).
                andExpect(jsonPath("$[1].transactionTo").value("Suresh")).
                andExpect(jsonPath("$[1].transactionAmount").value(11000.0)).
                andExpect(jsonPath("$[1].transactionRemarks").value("Friend"));
    }

    //testing findbyreceiver method
    @Test
    void testFindByReciever() throws Exception {
        TransactionNew transaction1=new TransactionNew(12323456L,new Date("10/20/2024"),"Ramesh","Suresh",11000.0,"Friend");
        TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
        TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

        List<TransactionNew> expectedList= Stream.of(transaction1,transaction3).collect(Collectors.toList());

        when(transactionService.apiFindByReciever("Suresh")).thenReturn(expectedList);

        //test case failed because the actual and expected entries are different
//        mockMvc.perform(get("/transaction/jdbc/receiver/Ramesh")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(12323456L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].transactionBy").value("Ramesh")).
//                andExpect(jsonPath("$[0].transactionTo").value("Suresh")).
//                andExpect(jsonPath("$[0].transactionAmount").value(11000.0)).
//                andExpect(jsonPath("$[0].transactionRemarks").value("Friend"));

        //test case passed because the actual and expected entries are similar
        mockMvc.perform(get("/transaction/jdbc/receiver/Suresh")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[1].transactionId").value(12323456L)).
                andExpect(jsonPath("$[1].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
                andExpect(jsonPath("$[1].transactionBy").value("Ramesh")).
                andExpect(jsonPath("$[1].transactionTo").value("Suresh")).
                andExpect(jsonPath("$[1].transactionAmount").value(11000.0)).
                andExpect(jsonPath("$[1].transactionRemarks").value("Friend"));

    }


    @Test
    void testFindByAmount() throws Exception {
        TransactionNew transaction1=new TransactionNew(12323456L,new Date("10/20/2024"),"Ramesh","Suresh",11000.0,"Friend");
        TransactionNew transaction2=new TransactionNew(765423436L,new Date("11/8/2024"),"Venkatesh","Prajwal",32000.0,"Health");
        TransactionNew transaction3=new TransactionNew(176543343L,new Date("24/9/2024"),"Razi","Darshan",25000.0,"Education");

        List<TransactionNew> expectedList= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        when(transactionService.apiFindByAmount(11000.0)).thenReturn(expectedList);

        //test case failed because the specified url is different from expected value.
        mockMvc.perform(get("/transaction/jdbc/amount/12000.0")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].transactionId").value(12323456L)).
                andExpect(jsonPath("$[0].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
                andExpect(jsonPath("$[0].transactionBy").value("Ramesh")).
                andExpect(jsonPath("$[0].transactionTo").value("Suresh")).
                andExpect(jsonPath("$[0].transactionAmount").value(11000.0)).
                andExpect(jsonPath("$[0].transactionRemarks").value("Friend"));

        //test case passed because the actual and expected entries are similar
//        mockMvc.perform(get("/transaction/jdbc/amount/11000.0")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[1].transactionId").value(12323456L)).
//                andExpect(jsonPath("$[1].transactionDate").value("2024-10-19T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[1].transactionBy").value("Ramesh")).
//                andExpect(jsonPath("$[1].transactionTo").value("Suresh")).
//                andExpect(jsonPath("$[1].transactionAmount").value(11000.0)).
//                andExpect(jsonPath("$[1].transactionRemarks").value("Friend"));

    }

}
