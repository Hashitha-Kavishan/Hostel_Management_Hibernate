package bo.impl;

import bo.SuperBO;
import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.impl.RoomDAOImpl;
import dao.impl.StudentDAOImpl;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;


public class RoomBOImpl implements RoomBO, SuperBO {

    private final RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        return roomDAO.add(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getRoom_type(),
                roomDTO.getMonthly_rent(),
                roomDTO.getRoom_qty()
        ));
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getRoom_type(),
                roomDTO.getMonthly_rent(),
                roomDTO.getRoom_qty()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> allStudents = new ArrayList<>();
        ArrayList<Room> all = roomDAO.getAll();
        for (Room room : all) {
            allStudents.add(new RoomDTO(room.getRoom_id(),room.getRoom_type(),room.getMonthly_rent(),room.getRoom_qty()));
        }
        return allStudents;
    }

    @Override
    public boolean ifRoomExist(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.ifRoomExist(id);
    }
}
