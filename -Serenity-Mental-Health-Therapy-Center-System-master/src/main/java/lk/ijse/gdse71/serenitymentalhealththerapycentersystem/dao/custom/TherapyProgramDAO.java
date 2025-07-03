package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.CrudDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapyProgramDAO  extends CrudDAO<TherapyProgram> {

    TherapyProgram getProgramId(String programId);

    ArrayList<String> getAllProgramNames();

    String getProgramIdByName(String selectedProgramName);

    String getProgramNameById(String programId);

    Optional<TherapyProgram> findByPK(String programId);

    List<String> getRegisteredProgramsByPatientId(String patientId);

    double getProgramFeeById(String programId);

    int getProgramCount();
}
