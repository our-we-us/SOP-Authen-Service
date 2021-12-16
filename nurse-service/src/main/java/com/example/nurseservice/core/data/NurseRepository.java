package com.example.nurseservice.core.data;


import com.example.nurseservice.core.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NurseRepository extends JpaRepository<NurseEntity, String>{
    NurseEntity findByNurseId(String nurseId);
}
