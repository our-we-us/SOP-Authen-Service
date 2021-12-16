package com.example.nurseservice.query.rest;

import com.example.nurseservice.query.FindNursesQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/nurses")
public class NurseQueryController {
    @Autowired
    QueryGateway queryGateway;
    @GetMapping
    public List<NurseRestModel> getNurses(){
        FindNursesQuery findNursesQuery = new FindNursesQuery();
        List<NurseRestModel> nurses = queryGateway.query(findNursesQuery, ResponseTypes.multipleInstancesOf(NurseRestModel.class)).join();
        return nurses;
    }
}
