package com.yashEE.journalApp.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Document(collection = "journal_entries")
@Data
public class JournalEntry {


    private ObjectId id;

    private String title;

    private LocalDateTime date;

    private String content;
}
