package in.dhirajrajput.service;

import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;


    public void saveEntry(JournalEntry journalEntry){
        this.journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> findAll(){
        return this.journalEntryRepo.findAll();
    }

    public void deleteEntry(JournalEntry journalEntry){
        this.deleteEntry(journalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId objectId){
        return this.journalEntryRepo.findById(objectId);
    }

    public boolean deleteEntityById(ObjectId id){
        this.journalEntryRepo.deleteById(id);
        return true;
    }
}
