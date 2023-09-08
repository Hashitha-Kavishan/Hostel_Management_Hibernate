package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyMoneyDTO {
    private String id;
    private String fullName;
    private String contact;
    private String room_id;
}
