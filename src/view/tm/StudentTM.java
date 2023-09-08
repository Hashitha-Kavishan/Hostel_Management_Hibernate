package view.tm;

import lombok.*;

import javax.persistence.Column;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentTM implements Comparable<StudentTM> {
    private String id;
    private String fullName;
    private String address;
    private String contactNo;
    private String dob;
    private String sex;

    @Override
    public int compareTo(StudentTM o) {
        return id.compareTo(o.getId());
    }
}
