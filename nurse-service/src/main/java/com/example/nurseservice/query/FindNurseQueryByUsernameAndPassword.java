package com.example.nurseservice.query;

import lombok.Data;
@Data
public class FindNurseQueryByUsernameAndPassword {
    private String username;
    private String password;
}
