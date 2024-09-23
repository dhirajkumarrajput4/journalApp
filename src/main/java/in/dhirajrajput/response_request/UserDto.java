package in.dhirajrajput.response_request;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
public class UserDto {
    private ObjectId id;
    private String userName;
    private String password;
    private String email;
    private boolean sentimentAnalysis;
}
