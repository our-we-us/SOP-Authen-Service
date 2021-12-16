package com.example.nurseservice.command.rest;

import lombok.Data;

@Data
public class UpdateNurseRestModel {
    private String patientId;
    private String nid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
