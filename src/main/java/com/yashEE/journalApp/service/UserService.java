package com.yashEE.journalApp.service;

import com.yashEE.journalApp.entity.JournalEntry;
import com.yashEE.journalApp.entity.Users;
import com.yashEE.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepo userRepo;


    public  void saveEntry(Users users){
        userRepo.save(users);
    }


    public List<Users> getAll (){
        return userRepo.findAll();
    }

    public Optional<Users> getbyId (ObjectId id){
        return userRepo.findById(id);
    }


    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public Users findUserName(String userName){
        return userRepo.findByUsername(userName);
    }
}
