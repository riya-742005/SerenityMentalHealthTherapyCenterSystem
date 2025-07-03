package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.CrudDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;
import org.hibernate.Session;

import java.util.Optional;

public interface TherapySessionDAO extends CrudDAO<TherapySession> {
    Optional<TherapySession> findByPK(String sessionId);

    boolean saveSessionWithPayment(Session session, TherapySession therapySession);

    TherapySessionDTO getSessionById(String sessionId);

    TherapySession getSessionId(String sessionId);

    int getSessionCountForToday();

    String getPatientNameBySessionId(String sessionId);

    String getProgramNameBySessionId(String sessionId);

    String getDescriptionBySessionId(String sessionId);
}
