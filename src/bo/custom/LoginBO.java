package bo.custom;

import java.sql.SQLException;

public interface LoginBO {
    boolean ifUserExists(String userName, String Password) throws SQLException, ClassNotFoundException;

    boolean changeUsername(String oldUN, String newUN)throws SQLException, ClassNotFoundException;

    boolean changePassword(String UN,String pw)throws SQLException, ClassNotFoundException;

    boolean ifUserExists(String userName) throws SQLException, ClassNotFoundException;

}
