package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapyProgramDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProgramBO extends SuperBO {
    ArrayList<TherapyProgramDTO> getAllPrograms();

    boolean saveProgram(TherapyProgramDTO therapyProgramDTO);

    boolean updateProgram(TherapyProgramDTO therapyProgramDTO);

    boolean deleteProgram(String programId);

    String getNextProgramId();

    List<TherapyProgramDTO> searchProgram(String searchText);

    ArrayList<String> getAllProgramsNames();

    String getProgramIdByName(String selectedProgramName);

    String getProgramNameById(String programId);

    List<String> getRegisteredProgramsByPatientId(String patientId);

    double getProgramFeeById(String programId);
}
