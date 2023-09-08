package dao.impl;

import dao.custom.ReserveDAO;
import dto.KeyMoneyDTO;
import entity.Reserve;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT res_id FROM reserve ORDER BY res_id DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s!=null) {
            int newCourseId = Integer.parseInt(s.replace("RS", "")) + 1;
            return String.format("RS%03d", newCourseId);
        }
        return "RS001";
    }

    @Override
    public ArrayList<Reserve> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Reserve> allReserve = new ArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM reserve ");
        allReserve = (ArrayList<Reserve>) query.list();
        transaction.commit();
        session.close();
        return allReserve;
    }

    @Override
    public boolean ifReserveExist(String Id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT res_id FROM reserve WHERE res_id=:Id");
        String id1 = (String) query.setParameter("Id", Id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean add(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        Student student = entity.getStudent();
        student.getResList().add(entity);
        Room room = entity.getRoom();
        room.setRoom_qty(room.getRoom_qty()-1);
        room.getResList().add(entity);
        session.update(student);
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        Room room = entity.getRoom();
        room.setRoom_qty(room.getRoom_qty()-1);
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reserve reserve = session.load(Reserve.class, s);

        session.delete(reserve);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reserve find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        return null;
    }

    @Override
    public Reserve get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reserve reserve = session.get(Reserve.class, s);
        transaction.commit();
        session.close();
        return reserve;
    }

    public ArrayList<KeyMoneyDTO> getAllPending() {
        ArrayList<KeyMoneyDTO> allPending = new ArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query  = session.createSQLQuery(" SELECT r.id,s.full_name,s.contactNo,r.room_id FROM reserve r INNER JOIN student s ON r. id=s.id WHERE r.key_money=0 ");
        ArrayList<Object[]> details = (ArrayList<Object[]>) query.list();
        transaction.commit();
        session.close();

        for (Object[] temp:details) {
            allPending.add(new KeyMoneyDTO(
                    (String) temp[0],
                    (String) temp[1],
                    (String) temp[2],
                    (String) temp[3]
            ));
        }

        return allPending;
    }
}
