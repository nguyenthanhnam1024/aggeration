package com.example.demo12security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@AllArgsConstructor
@IdClass(RoleUserId.class)
public class RoleUser {
    @Id
    private Long idUser;
    @Id
    private Long idRole;

    public RoleUser() {
    }
}
