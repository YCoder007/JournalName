package com.yashEE.journalApp.controller;

import com.yashEE.journalApp.entity.JournalEntry;
import com.yashEE.journalApp.entity.Users;
import com.yashEE.journalApp.service.JournalEntryService;
import com.yashEE.journalApp.service.UserService;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
@Data
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;




    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username){

        Users userName = userService.findUserName(username);
        List<JournalEntry> journalEntries = userName.getJournalEntries();

        if (journalEntries!=null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username ){

       try {

               journalEntryService.saveEntry(myEntry,username);
               return new ResponseEntity<>(myEntry, HttpStatus.CREATED);


       } catch (Exception e) {
       return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }


    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> findJournalEntryId (@PathVariable ObjectId id){

        Optional<JournalEntry> journalEntry = journalEntryService.getbyId(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{username}/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username){
        JournalEntry old= journalEntryService.getbyId(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null  &&! newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
        }

        journalEntryService.saveEntry(old);

        return null;
    }

    @DeleteMapping("id/{username}/{id}")
    public ResponseEntity<?>  deleteEntry(@PathVariable ObjectId id , @PathVariable String username){
        journalEntryService.deleteById(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
