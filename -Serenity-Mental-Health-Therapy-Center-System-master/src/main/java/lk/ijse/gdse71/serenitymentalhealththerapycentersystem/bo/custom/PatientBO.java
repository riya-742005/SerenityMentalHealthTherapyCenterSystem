package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;

import java.util.ArrayList;
import java.util.List;

public interface PatientBO extends SuperBO {
   String getNextPatientId();

    boolean savePatient(PatientDTO patientDTO);

    ArrayList<PatientDTO> getAllPatients();

    boolean updatePatient(PatientDTO patientDTO);

    boolean deletePatient(String patientId);


 List<PatientDTO> searchPatient(String searchText);

    ArrayList<String> getAllPatientNames();

    String getPatientNameById(String patientId);

    String getPatientIdByName(String selectedPatientName);
}
