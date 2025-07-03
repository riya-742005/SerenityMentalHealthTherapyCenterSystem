package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.CrudDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;

import java.util.ArrayList;
import java.util.Optional;

public interface PatientDAO extends CrudDAO<Patient> {

    ArrayList<String> getAllPatientNames();


    String getPatientNameById(String patientId);


    String getPatientIdByName(String selectedPatientName);

    Patient getPatientId(String patientId);

    Optional<Patient> findByPK(String patientId);

    int getPatientCount();
}
