package in.dhirajrajput.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)         //set properties for auto indexing in properties file
    @NonNull
    private String userName;

    @NonNull
    private String password;

    private String email;

    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;

}
