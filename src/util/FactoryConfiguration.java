package util;

import entity.Login;
import entity.Reserve;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reserve.class)
                .addAnnotatedClass(Login.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
