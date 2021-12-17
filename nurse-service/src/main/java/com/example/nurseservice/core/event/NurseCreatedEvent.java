package com.example.nurseservice.core.event;

import lombok.Data;
@Data
public class NurseCreatedEvent {
    private String nurseId;
    private String nid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
