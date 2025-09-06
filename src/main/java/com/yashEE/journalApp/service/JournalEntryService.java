package com.yashEE.journalApp.service;

import com.yashEE.journalApp.entity.JournalEntry;
import com.yashEE.journalApp.entity.Users;
import com.yashEE.journalApp.repository.JournalEntryRwepo;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class JournalEntryService {

    @Autowired
    private JournalEntryRwepo journalEntryRwepo;
    @Autowired
    private UserService userService;

    @Transactional
    public  void saveEntry(JournalEntry journalEntry, String users){
        Users userss = userService.findUserName(users);

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRwepo.save(journalEntry);

        userss.getJournalEntries().add(saved);
        userService.saveEntry(userss);
    }

    public  void saveEntry(JournalEntry journalEntry){

        journalEntryRwepo.save(journalEntry);

    }

    public List<JournalEntry> getAll (){
        return journalEntryRwepo.findAll();
    }

    public Optional<JournalEntry> getbyId (ObjectId id){
        return journalEntryRwepo.findById(id);
    }
    public void deleteById(ObjectId id, String username){

        Users users = userService.findUserName(username);
        users.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(users);
        journalEntryRwepo.deleteById(id);
    }

}
