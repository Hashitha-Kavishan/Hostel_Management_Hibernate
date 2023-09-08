package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String id;
    private String fullName;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;
}
