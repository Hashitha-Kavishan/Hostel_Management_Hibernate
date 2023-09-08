package dao;

import dao.impl.LoginDAOImpl;
import dao.impl.ReserveDAOImpl;
import dao.impl.RoomDAOImpl;
import dao.impl.StudentDAOImpl;

public class DAOFactory {
//    private static DAOFactory daoFactory;
//
//    private DAOFactory(){}
//    public static DAOFactory getInstance(){
//        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
//    }
//
//    public <T extends SuperDAO>T getDAO(DAOType daoType){
//        switch (daoType){
//            case STUDENT:
//                return (T) new StudentDAOImpl();
//            case LOGIN:
//                return (T) new LoginDAOImpl();
//            case ROOM:
//                return (T) new RoomDAOImpl();
//            default:
//                return null;
//        }
//    }

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, LOGIN, ROOM , RESERVE
    }
}
