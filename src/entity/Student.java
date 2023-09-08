package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class Student implements SuperEntity{
    @Id
    private String id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contactNo;
    @Column(columnDefinition = "DATE")
    private String dob;
    private String gender;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER/*,cascade = CascadeType.ALL*/)
    private List<Reserve> resList = new ArrayList<>();

    public Student(String id, String fullName, String address, String contactNo, String dob, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }
}
