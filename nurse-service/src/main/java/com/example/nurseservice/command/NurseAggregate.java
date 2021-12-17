package com.example.nurseservice.command;


import com.example.nurseservice.core.event.NurseCreatedEvent;
import com.example.nurseservice.core.event.NurseDeletedEvent;
import com.example.nurseservice.core.event.NurseUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class NurseAggregate {
    @AggregateIdentifier
    private String nurseId;
    private String nid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public NurseAggregate(){}

    @CommandHandler
    public NurseAggregate(CreateNurseCommand createNurseCommand){

        if(createNurseCommand.getNid() == null || createNurseCommand.getNid().isBlank()){
            throw new IllegalArgumentException("nid cannot be empty");
        }

        if(createNurseCommand.getFirstname() == null || createNurseCommand.getFirstname().isBlank()){
            throw new IllegalArgumentException("Firstname cannot be empty");
        }

        if(createNurseCommand.getLastname() == null || createNurseCommand.getLastname().isBlank()){
            throw new IllegalArgumentException("Lastname cannot be empty");
        }

        if(createNurseCommand.getUsername() == null || createNurseCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if(createNurseCommand.getPassword() == null || createNurseCommand.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }


        NurseCreatedEvent nurseCreatedEvent = new NurseCreatedEvent();
        BeanUtils.copyProperties(createNurseCommand, nurseCreatedEvent);
        AggregateLifecycle.apply(nurseCreatedEvent);
    }

    @EventSourcingHandler
    public void on(NurseCreatedEvent nurseCreatedEvent){
        this.nurseId = nurseCreatedEvent.getNurseId();
        this.nid = nurseCreatedEvent.getNid();
        this.firstname = nurseCreatedEvent.getFirstname();
        this.lastname = nurseCreatedEvent.getLastname();
        this.username = nurseCreatedEvent.getUsername();
        this.password = nurseCreatedEvent.getPassword();
    }

    @CommandHandler
    public void handle(DeleteNurseCommand deleteNurseCommand){
        NurseDeletedEvent nurseDeletedEvent = new NurseDeletedEvent();
        BeanUtils.copyProperties(deleteNurseCommand, nurseDeletedEvent);
        AggregateLifecycle.apply(nurseDeletedEvent);
    }

    @EventSourcingHandler
    public void on(NurseDeletedEvent nurseDeletedEvent){}

    @CommandHandler
    public void handle(UpdateNurseCommand updateNurseCommand){
        NurseUpdatedEvent nurseUpdatedEvent = new NurseUpdatedEvent();
        BeanUtils.copyProperties(updateNurseCommand, nurseUpdatedEvent);
        AggregateLifecycle.apply(nurseUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(NurseUpdatedEvent nurseUpdatedEvent){
        this.nid = nurseUpdatedEvent.getNid();
        this.firstname = nurseUpdatedEvent.getFirstname();
        this.lastname = nurseUpdatedEvent.getLastname();
        this.username = nurseUpdatedEvent.getUsername();
        this.password = nurseUpdatedEvent.getPassword();

    }
}
