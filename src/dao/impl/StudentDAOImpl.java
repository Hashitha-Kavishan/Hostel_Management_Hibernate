package dao.impl;

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

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
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

        Student student = session.load(Student.class, s);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }

    @Override
    public Student get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, s);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public String generateNewID(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT id FROM student ORDER BY id DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s!=null) {
            int newCourseId = Integer.parseInt(s.replace("S", "")) + 1;
            return String.format("S%03d", newCourseId);
        }
        return "S001";
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("FROM student ").list();
        transaction.commit();
        session.close();
        return (ArrayList<Student>) list;
    }

    @Override
    public boolean ifStudentExist(String Id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id FROM student WHERE id=:Id");
        String id1 = (String) query.setParameter("Id", Id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
