package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.DuplicateException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<TherapyProgram> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapyProgram> query = session.createQuery("from TherapyProgram ", TherapyProgram.class);
        return query.list();
    }

    @Override
    public boolean save(TherapyProgram therapyProgram) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram existsProgram = session.get(TherapyProgram.class, therapyProgram.getId());
            if (existsProgram != null) {
                throw new DuplicateException("Program already exists");
            }
            session.persist(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TherapyProgram therapyProgram) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String programId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram program = session.get(TherapyProgram.class, programId);
            if (program == null) {
                throw new NotFoundException("Program not found");
            }

            session.remove(program);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT p.id FROM TherapyProgram p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(2)) + 1;
            return String.format("PR%03d", newId);
        } else {
            return "PR001";
        }
    }

    @Override
    public List<TherapyProgram> search(String searchText) {
        Session session = factoryConfiguration.getSession();
        List<TherapyProgram> programs;

        try {
            programs = session.createQuery(
                            "FROM TherapyProgram p WHERE " +
                                    "p.id LIKE :searchText OR " +
                                    "p.programName LIKE :searchText",
                            TherapyProgram.class)
                    .setParameter("searchText", "%" + searchText + "%")
                    .getResultList();
        } finally {
            session.close();
        }
        return programs;
    }

    @Override
    public TherapyProgram getProgramId(String programId) {
        Session session = factoryConfiguration.getSession();
        TherapyProgram program = null;

        try {
            program = session.get(TherapyProgram.class, programId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return program;
    }

    @Override
    public ArrayList<String> getAllProgramNames() {
        Session session = factoryConfiguration.getSession();
        ArrayList<String> programNames = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<String> names = session.createQuery("select p.programName from TherapyProgram p", String.class).getResultList();

            programNames.addAll(names);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return programNames;


    }

    @Override
    public String getProgramIdByName(String selectedProgramName) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String programId = null;

        try {
            transaction = session.beginTransaction();
            programId = session.createQuery("SELECT p.id FROM TherapyProgram p WHERE p.programName = :programName", String.class)
                    .setParameter("programName", selectedProgramName)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return programId;

    }

    @Override
    public String getProgramNameById(String programId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String programName = null;

        try {
            transaction = session.beginTransaction();

            TherapyProgram program = session.get(TherapyProgram.class, programId);

            if (program != null) {
                programName = program.getProgramName();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return programName;
    }

    @Override
    public Optional<TherapyProgram> findByPK(String programId) {
        Session session = factoryConfiguration.getSession();
        TherapyProgram program = session.get(TherapyProgram.class, programId);
        return Optional.ofNullable(program);
    }

    @Override
    public List<String> getRegisteredProgramsByPatientId(String patientId) {
        Session session = factoryConfiguration.getSession();
        List<String> programNames = new ArrayList<>();

        try {
            String hql = "SELECT p.programName " +
                    "FROM Registration r " +
                    "JOIN r.therapyProgram p " +
                    "WHERE r.patient.id = :patientId";

            Query query = session.createQuery(hql, String.class);
            query.setParameter("patientId", patientId);

            programNames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return programNames;
    }

    @Override
    public double getProgramFeeById(String programId) {
        Session session = factoryConfiguration.getSession();

        String hql = "SELECT p.fee FROM TherapyProgram p WHERE p.id = :programId";
        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("programId", programId);
        return query.uniqueResult();

    }

    @Override
    public int getProgramCount() {
        Session session = factoryConfiguration.getSession();

        try{
            Long count = session.createQuery("SELECT COUNT(pr) FROM TherapyProgram pr", Long.class)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
