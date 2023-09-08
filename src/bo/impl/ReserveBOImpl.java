package bo.impl;

import bo.SuperBO;
import bo.custom.ReserveBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dao.impl.ReserveDAOImpl;
import dto.KeyMoneyDTO;
import dto.ReserveDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Reserve;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReserveBOImpl implements ReserveBO, SuperBO {

    private final ReserveDAOImpl reserveDAO = (ReserveDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO sDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(ReserveDTO reserveDTO) throws Exception {
        StudentDTO sDto = reserveDTO.getStudent();
        RoomDTO rDto = reserveDTO.getRoom();
        return reserveDAO.add(new Reserve(
                reserveDTO.getRes_id(),
                reserveDTO.getDate(),
                reserveDTO.getKey_money(),
                new Student(sDto.getId(),sDto.getFullName(),sDto.getAddress(),sDto.getContactNo(),sDto.getDob(),sDto.getGender()),
                new Room(rDto.getRoom_id(),rDto.getRoom_type(),rDto.getMonthly_rent(),rDto.getRoom_qty())
        ));
    }

    @Override
    public boolean update(ReserveDTO reserveDTO) throws Exception {
        StudentDTO sDto = reserveDTO.getStudent();
        RoomDTO rDto = reserveDTO.getRoom();
        return reserveDAO.update(new Reserve(
                reserveDTO.getRes_id(),
                reserveDTO.getDate(),
                reserveDTO.getKey_money(),
                new Student(sDto.getId(),sDto.getFullName(),sDto.getAddress(),sDto.getContactNo(),sDto.getDob(),sDto.getGender()),
                new Room(rDto.getRoom_id(),rDto.getRoom_type(),rDto.getMonthly_rent(),rDto.getRoom_qty())
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return reserveDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return reserveDAO.generateNewID();
    }

    @Override
    public ArrayList<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<ReserveDTO> resList = new ArrayList<>();
        resList.addAll(reserveDAO.getAll().stream().map(reserve -> {
            Student s = reserve.getStudent();
            Room r = reserve.getRoom();
            return new ReserveDTO(reserve.getRes_id(),reserve.getDate(),reserve.getKey_money(),
                    new StudentDTO(s.getId(), s.getFullName(), s.getAddress(), s.getContactNo(), s.getDob(), s.getGender()),
                    new RoomDTO(r.getRoom_id(), r.getRoom_type(), r.getMonthly_rent(), r.getRoom_qty()));
        }).collect(Collectors.toList()));
        return resList;
    }

    @Override
    public boolean ifReserveExist(String id) throws SQLException, ClassNotFoundException {
        return reserveDAO.ifReserveExist(id);
    }

    @Override
    public RoomDTO getRoomDetails(String id) throws Exception {
        System.out.println("pass getRoomDetails");
        Room room = roomDAO.get(id);
        return new RoomDTO(room.getRoom_id(), room.getRoom_type(), room.getMonthly_rent(), room.getRoom_qty());
    }

    @Override
    public StudentDTO getStudentDetails(String id) throws Exception {
        System.out.println("pass getStudentDetails");
        Student student = sDAO.get(id);
        return new StudentDTO(student.getId(), student.getFullName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender());
    }

    @Override
    public ArrayList<KeyMoneyDTO> getPendingKM() throws SQLException, ClassNotFoundException {
        ArrayList<KeyMoneyDTO> pendingList = new ArrayList<>();
        pendingList.addAll(reserveDAO.getAllPending());
        return pendingList;
    }
}
