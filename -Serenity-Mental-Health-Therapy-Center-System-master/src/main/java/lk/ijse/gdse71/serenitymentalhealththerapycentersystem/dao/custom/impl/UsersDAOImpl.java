package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.DuplicateException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.UsersDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public String getNextId() {
       Session session = factoryConfiguration.getSession();
       String nextId = null;

       try{
           nextId = session
                   .createQuery("SELECT u.id from Users u ORDER BY u.id DESC",String.class)
                   .setMaxResults(1)
                   .uniqueResult();
       }finally{
           session.close();
       }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("U%03d", newId);
        } else {
            return "U001";
        }
    }

    @Override
    public boolean save(Users entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Users existsUser = session.get(Users.class, entity.getId());
            if(existsUser != null){
                throw new DuplicateException("User already exists");
            }
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Users> getAll() {
        return List.of();
    }

    @Override
    public boolean update(Users entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Users> search(String searchText) {
        return List.of();
    }

    @Override
    public Users findByUserName(String userName) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        Users users = null;

        try{
            transaction = session.beginTransaction();

            Query<Users> query = session.createQuery("from Users where username = :userName", Users.class);
            query.setParameter("userName", userName);

            users = query.uniqueResult();
           transaction.commit();

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return users;

    }

    @Override
    public Users findByUserId(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        Users users = null;

        try{
            transaction = session.beginTransaction();

            Query<Users> query = session.createQuery("from Users where id = :id", Users.class);
            query.setParameter("id", id);

            users = query.uniqueResult();
            transaction.commit();

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return users;

    }
}
