package com.example.nurseservice.query;

import com.example.nurseservice.core.NurseEntity;
import com.example.nurseservice.core.data.NurseRepository;
import com.example.nurseservice.query.rest.NurseRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NurseQueryHandler {
    private final NurseRepository nurseRepository;
    public NurseQueryHandler(NurseRepository nurseRepository){
        this.nurseRepository = nurseRepository;
    }
    @QueryHandler
    List<NurseRestModel> findNurses(FindNursesQuery query){
        List<NurseRestModel> patientRest = new ArrayList<>();
        List<NurseEntity> storedNurses = nurseRepository.findAll();
        for (NurseEntity nurseEntity : storedNurses){
            NurseRestModel nurseRestModel = new NurseRestModel();
            BeanUtils.copyProperties(nurseEntity, nurseRestModel);
            patientRest.add(nurseRestModel);
        }
        return patientRest;
    }
}
