package in.dhirajrajput.response_request;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserDto {
    private ObjectId id;
    private String userName;
    private String password;
}
