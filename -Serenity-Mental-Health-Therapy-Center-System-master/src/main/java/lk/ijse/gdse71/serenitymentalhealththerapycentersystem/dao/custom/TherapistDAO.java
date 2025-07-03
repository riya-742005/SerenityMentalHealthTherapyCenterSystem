package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.CrudDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapistDAO extends CrudDAO<Therapist> {

    ArrayList<String> getAllTherapistNames();

    String getTherapistNameById(String therapistId);

    ArrayList<String> getTherapistNameByProgramId(String programId);

    Optional<Therapist> findByPK(String therapistId);

    String getTherapistIdByName(String selectedTherapistName);

    Therapist getTherapistId(String therapistId);

    int getTherapistCount();


}
