package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapySessionDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT ts.id FROM TherapySession ts ORDER BY ts.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("S%03d", newId);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean save(TherapySession entity) {
        return false;
    }

    @Override
    public List<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapySession> query = session.createQuery("from TherapySession ", TherapySession.class);
        return query.list();
    }

    @Override
    public boolean update(TherapySession entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(entity);
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
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            TherapySession therapySession = session.get(TherapySession.class,id);
            if(therapySession == null){
                throw new NotFoundException("Session not found");
            }
            session.remove(therapySession);
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
    public List<TherapySession> search(String searchText) {
        return List.of();
    }

    @Override
    public Optional<TherapySession> findByPK(String sessionId) {
        Session session = factoryConfiguration.getSession();
        TherapySession therapySession = session.get(TherapySession.class, sessionId);
        return Optional.ofNullable(therapySession);
    }

    @Override
    public boolean saveSessionWithPayment(Session session, TherapySession therapySession) {
        try {
            session.merge(therapySession);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
       Session session = factoryConfiguration.getSession();
       TherapySessionDTO therapySessionDTO = null;

       try{
           session.beginTransaction();

           TherapySession therapySession = session.get(TherapySession.class, sessionId);
           if(therapySession != null){
               therapySessionDTO = new TherapySessionDTO(
                       therapySession.getId(),
                       therapySession.getPatient().getId(),
                       therapySession.getTherapyProgram().getId(),
                       therapySession.getTherapist().getId(),
                       therapySession.getDescription(),
                       therapySession.getDate(),
                       therapySession.getSessionDate()

               );
           }
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
           if(session.getTransaction() != null){
               session.getTransaction().rollback();
           }
       }finally {
           session.close();
       }
       return therapySessionDTO;
    }

    @Override
    public TherapySession getSessionId(String sessionId) {
        Session session = factoryConfiguration.getSession();
        TherapySession therapySession = null;

        try {
            therapySession = session.get(TherapySession.class, sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return therapySession;
    }

    @Override
    public int getSessionCountForToday() {
      Session session = factoryConfiguration.getSession();
      Transaction transaction = null;
      try{
          transaction = session.beginTransaction();

          String hql = "SELECT COUNT(s.id) FROM TherapySession s WHERE s.sessionDate = :today";
          Long count = (Long) session.createQuery(hql)
                  .setParameter("today", LocalDate.now())
                  .uniqueResult();

          transaction.commit();

          return count != null ? count.intValue() : 0;

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
      return 0;
    }

    @Override
    public String getPatientNameBySessionId(String sessionId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String patient = null;

        try {
            transaction = session.beginTransaction();

            TherapySession therapySession = session.get(TherapySession.class, sessionId);

            if(therapySession != null){
                patient = therapySession.getPatient().getName();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return patient;
    }

    @Override
    public String getProgramNameBySessionId(String sessionId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String program = null;

        try {
            transaction = session.beginTransaction();

            TherapySession therapySession = session.get(TherapySession.class, sessionId);

            if(therapySession != null){
                program = therapySession.getTherapyProgram().getProgramName();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return program;
    }

    @Override
    public String getDescriptionBySessionId(String sessionId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        String desc = null;

        try {
            transaction = session.beginTransaction();

            TherapySession therapySession = session.get(TherapySession.class, sessionId);

            if(therapySession != null){
                desc = therapySession.getDescription();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return desc;
    }
}
