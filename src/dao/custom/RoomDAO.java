package dao.custom;

import dao.SuperDAO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDAO extends SuperDAO<Room, String> {
    ArrayList<Room> getAll() throws SQLException, ClassNotFoundException;

    boolean ifRoomExist(String id) throws SQLException, ClassNotFoundException;
}
