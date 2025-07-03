package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.RegistrationDTO;

import java.util.ArrayList;
import java.util.List;

public interface RegistrationBO extends SuperBO {
    ArrayList<RegistrationDTO> getAllRegistrations();

    String getNextRegistrationId();

    boolean saveRegistration(RegistrationDTO registrationDTO);

    boolean updateRegistration(RegistrationDTO registrationDTO);

    boolean deleteDetails(String regId);

    List<RegistrationDTO> searchRecord(String searchText);

    double getAdvancePaymentByPatientAndProgram(String patientId, String programId);
}
