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
@Entity
public class Room implements SuperEntity {
    @Id
    private String room_id;
    private String room_type;
    private double monthly_rent;
    private int room_qty;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER /*,cascade = CascadeType.ALL*/)
    private List<Reserve> resList = new ArrayList<>();

    public Room(String room_id, String room_type, double monthly_rent, int room_qty) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.monthly_rent = monthly_rent;
        this.room_qty = room_qty;
    }
}
