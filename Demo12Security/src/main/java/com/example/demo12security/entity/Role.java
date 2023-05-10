package com.example.demo12security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Role {
    @Id
    private Long idRole;
    private String roleName;

    public Role() {
    }

}
