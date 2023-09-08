package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reserve")
public class Reserve implements SuperEntity{
    @Id
    private String res_id;
    private String date;
    private double key_money;

    @ManyToOne(/*cascade = CascadeType.ALL,*/fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(/*cascade = CascadeType.ALL,*/fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;
}
