package com.example.demo12security.controller;

import com.example.demo12security.config.UserDetailsImp;
import com.example.demo12security.entity.Role;
import com.example.demo12security.entity.RoleUser;
import com.example.demo12security.entity.User;
import com.example.demo12security.jwt.JwtUtils;
import com.example.demo12security.repo.RoleRepo;
import com.example.demo12security.repo.RoleUserRepo;
import com.example.demo12security.repo.UserRepo;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class JwtController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    RoleUserRepo roleUserRepo;
    @PostMapping("/login")
    public String login(@Valid @RequestBody User user){
//        BCryptPasswordEncoder bc =  new BCryptPasswordEncoder();
//        String pass =bc.encode("nam");
//        User user1 = new User(1l, "nam", "nam@gmail.com", "$2a$10$rFFopr1gw6ArMNrHL82/neEDNQDx1/DG2QjgUaESSn7HCLAnnfIbS");
//        Role role = new Role(1l, "role user");
//        RoleUser roleUser = new RoleUser(1l, 1l);
//        userRepo.save(user1);
//        roleRepo.save(role);
//        roleUserRepo.save(roleUser);
//        return "abc";
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getPassWord()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwt((UserDetailsImp) authentication.getPrincipal());
        return jwt;
    }
    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
}

