package com.example.demo12security.repo;

import com.example.demo12security.entity.RoleUser;
import com.example.demo12security.entity.RoleUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserRepo extends JpaRepository<RoleUser, RoleUserId> {
    public List<RoleUser> findRoleUsersByIdUser(Long idUser);
}

