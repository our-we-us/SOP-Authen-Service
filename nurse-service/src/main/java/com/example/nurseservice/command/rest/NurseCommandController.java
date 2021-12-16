package com.example.nurseservice.command.rest;
import com.example.nurseservice.command.CreateNurseCommand;
import com.example.nurseservice.command.DeleteNurseCommand;
import com.example.nurseservice.command.UpdateNurseCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/register")
public class NurseCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public NurseCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    //create patient
    @PostMapping
    public String createNurse(@RequestBody CreateNurseRestModel model){



        CreateNurseCommand command = CreateNurseCommand.builder()
                .nurseId(UUID.randomUUID().toString())
                .nid(model.getNid())
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .username(model.getUsername())
                .password(model.getPassword())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }


        return result;

    }

    //delete patient by id
    @DeleteMapping("/{nurseId}")
    public String deleteNurse(@PathVariable(value = "nurseId") String patientId){

        DeleteNurseCommand command = DeleteNurseCommand.builder()
                .nurseId(patientId)
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    //update patient by id
    @PatchMapping(value = "/{nurseId}")
    public String updateNurse(@PathVariable(value = "nurseId") String nurseId, @RequestBody UpdateNurseRestModel model){
        UpdateNurseCommand command = UpdateNurseCommand.builder()
                .nurseId(nurseId)
                .nid(model.getNid())
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .username(model.getUsername())
                .password(model.getPassword())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
