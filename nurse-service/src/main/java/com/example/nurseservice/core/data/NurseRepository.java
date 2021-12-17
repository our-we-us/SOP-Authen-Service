package com.example.nurseservice.core.data;


import com.example.nurseservice.core.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NurseRepository extends JpaRepository<NurseEntity, String>{
    Optional<NurseEntity> findByUsername(String username);
}
