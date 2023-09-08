package view.tm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomTM {
    private String room_id;
    private String room_type;
    private double monthly_rent;
    private int room_qty;
}
