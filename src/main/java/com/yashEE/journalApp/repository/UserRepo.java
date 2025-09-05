package com.yashEE.journalApp.repository;

import com.yashEE.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface UserRepo extends MongoRepository<Users, ObjectId> {

    Users findByUsername(String userName);


}
