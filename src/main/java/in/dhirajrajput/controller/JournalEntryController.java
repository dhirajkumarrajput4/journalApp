package in.dhirajrajput.controller;


import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.service.JournalEntryService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllEntry() {
        List<JournalEntry> journalEntryList = this.journalEntryService.findAll();
        if (!journalEntryList.isEmpty()) {
            return new ResponseEntity<>(journalEntryList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<JournalEntry> getEntityById(@PathVariable("id") ObjectId id) {
        Optional<JournalEntry> journalEntryOptional = this.journalEntryService.findById(id);
        return journalEntryOptional.map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") ObjectId id) {
        this.journalEntryService.deleteEntityById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("byId/{id}")
    public ResponseEntity<JournalEntry> updateJournalEtity(@PathVariable("id") ObjectId id,
                                                           @RequestBody JournalEntry journalEntry) {
        JournalEntry existingEntity = this.journalEntryService.findById(id).orElse(null);
        if (existingEntity != null) {
            existingEntity.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : existingEntity.getContent());
            existingEntity.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ? journalEntry.getTitle() : existingEntity.getTitle());
            return new ResponseEntity<>(existingEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            this.journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


}
