package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Employee {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String nicNumber;
    private String userType;
}
