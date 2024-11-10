package com.spring.security.postgresql.repository;

import com.spring.security.postgresql.models.ERole;
import com.spring.security.postgresql.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
