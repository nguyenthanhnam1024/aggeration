package com.example.demo12security.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@Validated
public class User {

    @Id
    @NotNull
    private Long idUser;
    @NotNull
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String passWord;

    public User() {
    }
}
