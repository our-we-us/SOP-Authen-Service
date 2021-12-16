package com.example.nurseservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateNurseCommand {
    @TargetAggregateIdentifier
    private final String nurseId;
    private final String nid;
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String password;
}
