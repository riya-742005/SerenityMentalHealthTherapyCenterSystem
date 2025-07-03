package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.TherapistBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapistDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapyProgramDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);
    TherapyProgramDAO programDAO = (TherapyProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public ArrayList<TherapistDTO> getAllTherapist() {
        List<Therapist> therapists = therapistDAO.getAll();
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(
                    therapist.getId(),
                    therapist.getTherapyProgram().getId(),
                    therapist.getName(),
                    therapist.getAddress(),
                    therapist.getMobileNumber(),
                    therapist.getNic()

            ));
        }
        return therapistDTOS;
    }

    @Override
    public String getNextTherapistId() {
        return therapistDAO.getNextId();
    }


    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) {
       TherapyProgram therapyProgram = programDAO.getProgramId(therapistDTO.getProgramId());

       if(therapyProgram == null) {
           throw new RuntimeException("Program not found for ID: " + therapistDTO.getProgramId());
       }
       Therapist therapist = new Therapist(
               therapistDTO.getId(),
               therapistDTO.getName(),
               therapistDTO.getAddress(),
               therapistDTO.getMobileNumber(),
               therapistDTO.getNic(),
               therapyProgram
       );
       return therapistDAO.save(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) {
        TherapyProgram therapyProgram = programDAO.getProgramId(therapistDTO.getProgramId());

        if(therapyProgram == null) {
            throw new RuntimeException("Program not found for ID: " + therapistDTO.getProgramId());
        }
        Therapist therapist = new Therapist(
                therapistDTO.getId(),
                therapistDTO.getName(),
                therapistDTO.getAddress(),
                therapistDTO.getMobileNumber(),
                therapistDTO.getNic(),
                therapyProgram
        );
        return therapistDAO.update(therapist);
    }

    @Override
    public boolean deleteTherapist(String therapistId) {
        return therapistDAO.delete(therapistId);
    }

    @Override
    public List<TherapistDTO> searchTherapist(String searchText) {
        List<Therapist> therapists = therapistDAO.search(searchText);
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(
                    therapist.getId(),
                    therapist.getTherapyProgram().getId(),
                    therapist.getName(),
                    therapist.getAddress(),
                    therapist.getMobileNumber(),
                    therapist.getNic()

            ));
        }
        return therapistDTOS;
    }

    @Override
    public ArrayList<String> getAllTherapistNames() {
        return therapistDAO.getAllTherapistNames();
    }

    @Override
    public String getTherapistNameById(String therapistId) {
        return therapistDAO.getTherapistNameById(therapistId);
    }

    @Override
    public ArrayList<String> getTherapistNamesByProgramId(String programId) {
        return therapistDAO.getTherapistNameByProgramId(programId);
    }

    @Override
    public String getTherapistIdByName(String selectedTherapistName) {
        return therapistDAO.getTherapistIdByName(selectedTherapistName);
    }
}
