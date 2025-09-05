package com.yashEE.journalApp.controller;

import com.yashEE.journalApp.entity.Users;
import com.yashEE.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<Users>  findall(){

        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody Users users){
        userService.saveEntry(users);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users,@PathVariable String username){
        Users userName = userService.findUserName(username);
        if (userName!=null){
            userName.setUsername(users.getUsername());
            userName.setPassword(users.getPassword());

            userService.saveEntry(userName);
        } return new ResponseEntity<>(HttpStatus.NO_CONTENT);



    }




}
