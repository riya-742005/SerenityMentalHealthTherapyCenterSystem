package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;

import java.util.ArrayList;

public interface TherapySessionBO extends SuperBO {
    String getNextSessionId();

    ArrayList<TherapySessionDTO> getAllSessions();

    TherapySessionDTO getSessionById(String sessionId);

    boolean updateSession(TherapySessionDTO therapySessionDTO);

    boolean deleteSession(String sessionId);

    String getPatientNameBySessionId(String sessionId);

    String getProgramNameBySessionId(String sessionId);

    String getDescriptionBySessionId(String sessionId);
}
