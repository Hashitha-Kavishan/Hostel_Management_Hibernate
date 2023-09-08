package dao.impl;

import dao.custom.LoginDAO;
import entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.sql.SQLException;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean updateUser(String oldUN, String newUN) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Login SET userName=:newUsername where userName=:oldUsername ");
        query.setParameter("oldUsername", oldUN);
        query.setParameter("newUsername", newUN);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean changePassword(String UN,String pw) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Login l SET l.password=:newPassword where l.userName=:Username");
        query.setParameter("Username", UN);
        query.setParameter("newPassword", pw);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean userSearch(String userName, String Password) throws SQLException, ClassNotFoundException {

        boolean login=false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query= (Query) session.createQuery("SELECT userName,password from Login where userName=:username and password=:password");
        query.setParameter("username", userName);
        query.setParameter("password", Password);
        Object user=  query.uniqueResult();
        if(user!=null) {
            login=true;
        }else {
            login=false;
        }

        session.close();

        return login;
    }

    @Override
    public boolean userSearch(String userName) throws SQLException, ClassNotFoundException {

        boolean login=false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query= (Query) session.createQuery("SELECT userName,password from Login where userName=:username ");
        query.setParameter("username", userName);
        Object user=  query.uniqueResult();
        if(user!=null) {
            login=true;
        }else {
            login=false;
        }

        session.close();

        return login;
    }

    @Override
    public boolean add(Login entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Login entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Login find(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<Login> findAll() throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Login get(String s) throws Exception {
        return null;
    }
}
