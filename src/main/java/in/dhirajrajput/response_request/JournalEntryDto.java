package in.dhirajrajput.response_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntryDto {
    private String title;
    private String content;
    private LocalDateTime date;
}
