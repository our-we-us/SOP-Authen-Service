package com.example.nurseservice.core;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "nurse")
@Data
public class NurseEntity implements Serializable{
    private static final long serialVersionUID = -8909997925040858692L;
    @Id
    @Column(unique = true)
    private String nurseId;
    private String nid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
