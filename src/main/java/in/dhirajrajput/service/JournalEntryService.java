package in.dhirajrajput.service;

import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry entry = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(entry);
            userService.updateUser(user);
        } catch (Exception exception) {
            throw new RuntimeException("An error occured while saving the entry.");
        }

    }

    public List<JournalEntry> findAll() {
        return this.journalEntryRepo.findAll();
    }

    public void deleteEntry(JournalEntry journalEntry) {
        this.deleteEntry(journalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId objectId) {
        return this.journalEntryRepo.findById(objectId);
    }

    @Transactional
    public boolean deleteEntityById(ObjectId id, String userName) {
        try {
            User user = userService.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            this.journalEntryRepo.deleteById(id); 
            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occured while deleting the record");
        }

    }
}
