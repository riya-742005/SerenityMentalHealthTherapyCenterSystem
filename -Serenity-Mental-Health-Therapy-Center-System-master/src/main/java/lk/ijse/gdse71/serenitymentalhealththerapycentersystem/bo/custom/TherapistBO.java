package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;

import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO {
    ArrayList<TherapistDTO> getAllTherapist();

    String getNextTherapistId();


    boolean saveTherapist(TherapistDTO therapistDTO);

    boolean updateTherapist(TherapistDTO therapistDTO);

    boolean deleteTherapist(String therapistId);

    List<TherapistDTO> searchTherapist(String searchText);

    ArrayList<String> getAllTherapistNames();

    String getTherapistNameById(String therapistId);

    ArrayList<String> getTherapistNamesByProgramId(String programId);

    String getTherapistIdByName(String selectedTherapistName);
}
