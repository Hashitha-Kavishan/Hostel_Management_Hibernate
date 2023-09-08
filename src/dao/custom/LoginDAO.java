package dao.custom;

import dao.SuperDAO;
import entity.Login;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO<Login, String> {
    boolean userSearch(String userName, String Password) throws SQLException, ClassNotFoundException;
    boolean updateUser(String oldUN, String newUN) throws SQLException, ClassNotFoundException ;

    boolean userSearch(String userName) throws SQLException, ClassNotFoundException;

    boolean changePassword(String UN,String pw) throws SQLException, ClassNotFoundException ;
}
