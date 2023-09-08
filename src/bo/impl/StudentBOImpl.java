package bo.impl;


import bo.BOFactory;
import bo.SuperBO;
import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO, SuperBO {

private final StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();
        for (Student program : all) {
            allStudent.add(new StudentDTO(program.getId(),program.getFullName(),program.getAddress(),program.getContactNo(),program.getDob(),program.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDob(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDob(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewID() {
        return studentDAO.generateNewID();
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.ifStudentExist(id);
    }

}
