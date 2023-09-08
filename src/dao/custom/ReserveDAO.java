package dao.custom;

import dao.SuperDAO;
import entity.Reserve;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReserveDAO extends SuperDAO<Reserve, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
    ArrayList<Reserve> getAll() throws SQLException, ClassNotFoundException;
    boolean ifReserveExist(String id) throws SQLException, ClassNotFoundException;
}
