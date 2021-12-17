package com.example.nurseservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteNurseCommand {
    @TargetAggregateIdentifier
    private final String nurseId;
}
