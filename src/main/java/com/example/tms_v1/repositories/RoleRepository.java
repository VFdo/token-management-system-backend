package com.example.tms_v1.repositories;

import com.example.tms_v1.models.ERole;
import com.example.tms_v1.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
