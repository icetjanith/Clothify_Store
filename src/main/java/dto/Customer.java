package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String  title;
    private String  name;
    private String phone;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

}
