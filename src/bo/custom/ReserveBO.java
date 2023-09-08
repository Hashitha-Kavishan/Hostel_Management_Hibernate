package bo.custom;

import dto.KeyMoneyDTO;
import dto.ReserveDTO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ReserveBO {
    boolean add(ReserveDTO reserveDTO) throws Exception;

    boolean update(ReserveDTO reserveDTO) throws Exception;

    boolean delete(String id) throws Exception;

    String generateNewID() throws SQLException, ClassNotFoundException;

    ArrayList<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException;

    boolean ifReserveExist(String id) throws SQLException, ClassNotFoundException;

    RoomDTO getRoomDetails(String id) throws Exception;

    StudentDTO getStudentDetails(String id) throws Exception;

    ArrayList<KeyMoneyDTO> getPendingKM()throws SQLException, ClassNotFoundException;
}
