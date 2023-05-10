package com.example.demo12security.config;

import com.example.demo12security.entity.RoleUser;
import com.example.demo12security.entity.User;
import com.example.demo12security.repo.RoleRepo;
import com.example.demo12security.repo.RoleUserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class UserDetailsImp implements UserDetails {

    private Long idUser;
    private String userName;
    private String email;
    private String passWord;
    private Collection<GrantedAuthority> authorities;

    public UserDetailsImp
            (Long idUser, String userName, String email, String passWord, Collection<? extends GrantedAuthority> authorities) {
    }

    public UserDetailsImp() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    RoleUserRepo roleUserRepo;
    public UserDetailsImp build(User user){
        List<Long> longList = roleUserRepo.findRoleUsersByIdUser(user.getIdUser())
                .stream().map(roleUser -> roleUser.getIdRole()).collect(Collectors.toList());

        List<String> roleNameList = roleRepo.findAllByIdRoleIsIn(longList);
        for (String role : roleNameList){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UserDetailsImp
                (user.getIdUser(), user.getUserName(), user.getEmail(), user.getPassWord(), this.getAuthorities());
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
