package bo.custom;


import dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO {
    boolean add(RoomDTO roomDTO) throws Exception;

    boolean update(RoomDTO roomDTO) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;

    boolean ifRoomExist(String id)throws SQLException, ClassNotFoundException;
}
