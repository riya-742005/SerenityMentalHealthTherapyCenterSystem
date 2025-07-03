package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try{
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Therapist.class)
                    .addAnnotatedClass(TherapyProgram.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(TherapySession.class)
                    .addAnnotatedClass(Users.class)
                    .addAnnotatedClass(Registration.class)
                    ;
            sessionFactory = configuration.buildSessionFactory();
        }catch (Exception e){
            throw new RuntimeException("Failed to load hibernate.properties", e);
        }

    }
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
