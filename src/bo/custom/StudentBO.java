package bo.custom;



import bo.SuperBO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {

    boolean add(StudentDTO customerDTO) throws Exception;

    boolean update(StudentDTO customerDTO) throws Exception;

    boolean delete(String id) throws Exception;

    String generateNewID() throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
}
