package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.RegistrationBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.RegistrationDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.RegistrationDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Registration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    TherapyProgramDAO programDAO = (TherapyProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public ArrayList<RegistrationDTO> getAllRegistrations() {
        List<Registration> registrations = registrationDAO.getAll();
        ArrayList<RegistrationDTO> registrationDTOS = new ArrayList<>();

        for (Registration registration : registrations) {
            registrationDTOS.add(new RegistrationDTO(
                    registration.getId(),
                    registration.getPatient().getId(),
                    registration.getTherapyProgram().getId(),
                    registration.getDate(),
                    registration.getAdvancePayment()
            ));
        }
        return registrationDTOS;
    }

    @Override
    public String getNextRegistrationId() {
        return registrationDAO.getNextId();
    }

    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) {
        TherapyProgram therapyProgram = programDAO.getProgramId(registrationDTO.getProgramId());
        Patient patient = patientDAO.getPatientId(registrationDTO.getPatientId());

        if (therapyProgram == null || patient == null) {
            throw new RuntimeException("Program  or patient not found for ID: ");
        }
        Registration registration = new Registration(
                registrationDTO.getId(),
                registrationDTO.getDate(),
                registrationDTO.getAdvancePayment(),
                patient,
                therapyProgram
        );
        return registrationDAO.save(registration);
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) {
        TherapyProgram therapyProgram = programDAO.getProgramId(registrationDTO.getProgramId());
        Patient patient = patientDAO.getPatientId(registrationDTO.getPatientId());

        if (therapyProgram == null || patient == null) {
            throw new RuntimeException("Program  or patient not found for ID: ");
        }
        Registration registration = new Registration(
                registrationDTO.getId(),
                registrationDTO.getDate(),
                registrationDTO.getAdvancePayment(),
                patient,
                therapyProgram
        );
        return registrationDAO.update(registration);
    }

    @Override
    public boolean deleteDetails(String regId) {
        return registrationDAO.delete(regId);
    }

    @Override
    public List<RegistrationDTO> searchRecord(String searchText) {
        List<Registration> registrations = registrationDAO.search(searchText);
        ArrayList<RegistrationDTO> registrationDTOS = new ArrayList<>();

        for (Registration registration : registrations) {
            registrationDTOS.add(new RegistrationDTO(
                    registration.getId(),
                    registration.getPatient().getId(),
                    registration.getTherapyProgram().getId(),
                    registration.getDate(),
                    registration.getAdvancePayment()
            ));
        }
        return registrationDTOS;
    }

    @Override
    public double getAdvancePaymentByPatientAndProgram(String patientId, String programId) {
        return registrationDAO.getAdvancePaymentByPatientAndProgram(patientId,programId);
    }
}
