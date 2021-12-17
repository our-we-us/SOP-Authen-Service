package com.example.nurseservice.query;


import com.example.nurseservice.core.NurseEntity;
import com.example.nurseservice.core.data.NurseRepository;
import com.example.nurseservice.core.event.NurseCreatedEvent;
import com.example.nurseservice.core.event.NurseDeletedEvent;
import com.example.nurseservice.core.event.NurseUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class NurseEventsHandler {
    private final NurseRepository nurseRepository;
    public NurseEventsHandler(NurseRepository nurseRepository){
        this.nurseRepository = nurseRepository;
    }
    @EventHandler
    public void on(NurseCreatedEvent event) {
        NurseEntity nurseEntity = new NurseEntity();
        BeanUtils.copyProperties(event, nurseEntity);
        nurseRepository.save(nurseEntity);
    }

    @EventHandler
    public void on(NurseDeletedEvent event){
        Optional<NurseEntity> optionalNurseEntity = this.nurseRepository.findById(event.getNurseId());
        if (optionalNurseEntity.isPresent()){
            NurseEntity nurseEntity = optionalNurseEntity.get();
            nurseRepository.delete(nurseEntity);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @EventHandler
    public void on(NurseUpdatedEvent event){
        Optional<NurseEntity> optionalNurseEntity = this.nurseRepository.findById(event.getNurseId());
        if(optionalNurseEntity.isPresent()){
            NurseEntity nurseEntity = optionalNurseEntity.get();
            nurseEntity.setNid(event.getNid());
            nurseEntity.setFirstname(event.getFirstname());
            nurseEntity.setLastname(event.getLastname());
            nurseEntity.setUsername(event.getUsername());
            nurseEntity.setPassword(event.getPassword());

        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
