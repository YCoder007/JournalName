package com.yashEE.journalApp.repository;

import com.yashEE.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRwepo extends MongoRepository<JournalEntry, ObjectId> {
}
