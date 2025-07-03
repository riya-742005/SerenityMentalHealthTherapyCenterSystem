package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.DuplicateException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapistDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Therapist> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
        return query.list();
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("T%03d", newId);
        } else {
            return "T001";
        }
    }

    @Override
    public boolean save(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist existsTherapist = session.get(Therapist.class, therapist.getId());
            if(existsTherapist != null){
                throw new DuplicateException("Therapist already exists");
            }
            session.persist(therapist);
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
    public boolean update(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(therapist);
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
    public boolean delete(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,therapistId);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }

            session.remove(therapist);
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
    public List<Therapist> search(String searchText) {
        Session session = factoryConfiguration.getSession();
        List<Therapist> therapists;

        try{
            therapists = session.createQuery(
                            "FROM Therapist th WHERE " +
                                    "th.id LIKE :searchText OR " +
                                    "th.name LIKE :searchText OR " +
                                    "th.nic LIKE :searchText OR " +
                                    "CAST(th.mobileNumber AS string) LIKE :searchText",
                            Therapist.class)
                    .setParameter("searchText", "%" + searchText + "%")
                    .getResultList();
        }finally {
            session.close();
        }
        return therapists;
    }


    @Override
    public ArrayList<String> getAllTherapistNames() {
        Session session = factoryConfiguration.getSession();
        ArrayList<String> therapistNames = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<String> names = session.createQuery("select th.name from Therapist th", String.class).getResultList();

            therapistNames.addAll(names);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return therapistNames;
    }

    @Override
    public String getTherapistNameById(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String therapistName = null;

        try{
            transaction = session.beginTransaction();

            Therapist therapist = session.get(Therapist.class, therapistId);

            if(therapist != null) {
                therapistName = therapist.getName();
            }
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return therapistName;
    }

    @Override
    public ArrayList<String> getTherapistNameByProgramId(String programId) {
        Session session = factoryConfiguration.getSession();
        ArrayList<String> therapistNames = new ArrayList<>();

        try{
            List<String> names = session.createQuery(
                    "SELECT th.name FROM Therapist th WHERE th.therapyProgram.id = :programId", String.class)
                    .setParameter("programId",programId)
                    .getResultList();
            therapistNames.addAll(names);
        }finally {
            session.close();
        }
        return therapistNames;
    }

    @Override
    public Optional<Therapist> findByPK(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Therapist therapist = session.get(Therapist.class, therapistId);
        return Optional.ofNullable(therapist);
    }

    @Override
    public String getTherapistIdByName(String selectedTherapistName) {
        Session  session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String therapistId = null;

        try{
            transaction = session.beginTransaction();
            therapistId = session.createQuery("SELECT th.id FROM Therapist th WHERE th.name = :name",String.class)
                    .setParameter("name", selectedTherapistName)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return therapistId;
    }

    @Override
    public Therapist getTherapistId(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Therapist therapist = null;

        try {
            therapist = session.get(Therapist.class, therapistId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return therapist;
    }

    @Override
    public int getTherapistCount() {
        Session session = factoryConfiguration.getSession();

        try{
            Long count = session.createQuery("SELECT COUNT(th) FROM Therapist th", Long.class)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
