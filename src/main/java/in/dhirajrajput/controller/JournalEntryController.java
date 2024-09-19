package in.dhirajrajput.controller;


import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.entity.User;
import in.dhirajrajput.service.JournalEntryService;
import in.dhirajrajput.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/list/{userName}")
    public ResponseEntity<?> getAllEntryOfUser(@PathVariable("userName") String userName) {
        User user=userService.findByUserName(userName).orElseThrow(()->new RuntimeException("User not found"));
        List<JournalEntry> journalEntryList = user.getJournalEntries();
        if (!journalEntryList.isEmpty()) {
            return new ResponseEntity<>(journalEntryList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/byId/{id}/{useName}")
    public ResponseEntity<JournalEntry> getEntityById(@PathVariable("id") ObjectId id,@PathVariable("userName") String userName) {
        User user=userService.findByUserName(userName).orElseThrow(()->new RuntimeException("User not found"));

        Optional<JournalEntry> journalEntryOptional = user.getJournalEntries().stream().filter(entry->entry.getId().equals(id)).findFirst();
        return journalEntryOptional.map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/byId/{id}/{userName}")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") ObjectId id,@PathVariable("userName")String userName) {
        this.journalEntryService.deleteEntityById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("byId/{id}/{userName}")
    public ResponseEntity<JournalEntry> updateJournalEtity(@PathVariable("id") ObjectId id,@PathVariable("userName")String userName,
                                                           @RequestBody JournalEntry journalEntry) {
        JournalEntry existingEntity = this.journalEntryService.findById(id).orElse(null);
        if (existingEntity != null) {
            existingEntity.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : existingEntity.getContent());
            existingEntity.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ? journalEntry.getTitle() : existingEntity.getTitle());
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>(existingEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry,@PathVariable("userName") String userName) {
        try {
            this.journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


}
