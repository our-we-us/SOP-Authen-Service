package com.example.nurseservice.query;

import com.example.nurseservice.core.NurseEntity;
import com.example.nurseservice.core.data.NurseRepository;
import com.example.nurseservice.query.rest.NurseRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class NurseQueryHandler {
    private final NurseRepository nurseRepository;
    public NurseQueryHandler(NurseRepository nurseRepository){
        this.nurseRepository = nurseRepository;
    }
    @QueryHandler
    List<NurseRestModel> findNurses(FindNursesQuery query){
        List<NurseRestModel> nurseRest = new ArrayList<>();
        List<NurseEntity> storedNurses = nurseRepository.findAll();
        for (NurseEntity nurseEntity : storedNurses){
            NurseRestModel nurseRestModel = new NurseRestModel();
            BeanUtils.copyProperties(nurseEntity, nurseRestModel);
            nurseRest.add(nurseRestModel);
        }
        return nurseRest;
    }

    @QueryHandler
    NurseRestModel findNursesById(FindNursesQueryById query){

        Optional<NurseEntity> storedNurses = nurseRepository.findById(query.getNurseId());
        NurseRestModel nurseRestModel = new NurseRestModel();
        BeanUtils.copyProperties(storedNurses.get(), nurseRestModel);

        return nurseRestModel;
    }

    @QueryHandler
    NurseRestModel findNursesByUsername(FindNursesQueryByUsername query){

        Optional<NurseEntity> storedNurses = nurseRepository.findByUsername(query.getUsername());
        NurseRestModel nurseRestModel = new NurseRestModel();
        BeanUtils.copyProperties(storedNurses.get(), nurseRestModel);

        return nurseRestModel;
    }
}
