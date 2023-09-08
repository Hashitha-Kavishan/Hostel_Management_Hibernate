package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    private String res_id;
    private String date;
    private double key_money;
    private StudentDTO student;
    private RoomDTO room;

}
