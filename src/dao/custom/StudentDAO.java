package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends SuperDAO<Student, String> {
     String generateNewID() throws SQLException, ClassNotFoundException;
     ArrayList<Student> getAll() throws SQLException, ClassNotFoundException;
     boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
}
