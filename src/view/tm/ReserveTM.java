package view.tm;

import entity.Reserve;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReserveTM extends Reserve implements Comparable<ReserveTM>{
    private String res_id;
    private String date;
    private double key_money;
    private String student_id;
    private String room_id;

    @Override
    public int compareTo(ReserveTM o) {
        return res_id.compareTo(o.getRes_id());
    }
}
