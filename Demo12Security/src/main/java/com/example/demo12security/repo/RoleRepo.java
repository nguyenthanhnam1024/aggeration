package com.example.demo12security.repo;

import com.example.demo12security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<String> findAllByIdRoleIsIn(List<Long> listIdRole);
}

