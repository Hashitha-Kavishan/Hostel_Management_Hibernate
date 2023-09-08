package bo.impl;

import bo.SuperBO;
import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO , SuperBO {

    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean ifUserExists(String userName, String Password) throws SQLException, ClassNotFoundException {
        return loginDAO.userSearch(userName,Password);
    }

    @Override
    public boolean ifUserExists(String userName) throws SQLException, ClassNotFoundException {
        return loginDAO.userSearch(userName);
    }

    @Override
    public boolean changeUsername(String oldUN, String newUN) throws SQLException, ClassNotFoundException {
        return loginDAO.updateUser(oldUN,newUN);
    }

    @Override
    public boolean changePassword(String UN,String pw) throws SQLException, ClassNotFoundException {
        return loginDAO.changePassword(UN,pw);
    }
}
