package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String nicNumber;
    private String userType;

}
