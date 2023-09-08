package dao.impl;


import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, s);

        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Room> findAll() throws Exception {
        return null;
    }

    @Override
    public Room get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, s);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Room> allRooms = new ArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Room ");
        allRooms = (ArrayList<Room>) query.list();
        transaction.commit();
        session.close();
        return allRooms;
    }

    @Override
    public boolean ifRoomExist(String Id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT room_id FROM Room WHERE room_id=:Id");
        String id1 = (String) query.setParameter("Id", Id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
