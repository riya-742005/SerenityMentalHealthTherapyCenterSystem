package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.ProgramBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapyProgramDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public ArrayList<TherapyProgramDTO> getAllPrograms() {
        List<TherapyProgram> programs = therapyProgramDAO.getAll();
        ArrayList<TherapyProgramDTO> programDTOS = new ArrayList<>();

        for (TherapyProgram program : programs) {
            programDTOS.add(new TherapyProgramDTO(
                    program.getId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()

            ));
        }
        return programDTOS;
    }

    @Override
    public boolean saveProgram(TherapyProgramDTO therapyProgramDTO) {
        return therapyProgramDAO.save(new TherapyProgram(
                therapyProgramDTO.getId(),
                therapyProgramDTO.getProgramName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee()

        ));
    }

    @Override
    public boolean updateProgram(TherapyProgramDTO therapyProgramDTO) {
        return therapyProgramDAO.update(new TherapyProgram(
                therapyProgramDTO.getId(),
                therapyProgramDTO.getProgramName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee()
        ));
    }

    @Override
    public boolean deleteProgram(String programId) {
        return therapyProgramDAO.delete(programId);
    }

    @Override
    public String getNextProgramId() {
        return therapyProgramDAO.getNextId();
    }

    @Override
    public List<TherapyProgramDTO> searchProgram(String searchText) {
        List<TherapyProgram> programs = therapyProgramDAO.search(searchText);
        ArrayList<TherapyProgramDTO> programDTOS = new ArrayList<>();

        for (TherapyProgram program : programs) {
            programDTOS.add(new TherapyProgramDTO(
                    program.getId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()

            ));
        }
        return programDTOS;
    }

    @Override
    public ArrayList<String> getAllProgramsNames() {
        return therapyProgramDAO.getAllProgramNames();
    }

    @Override
    public String getProgramIdByName(String selectedProgramName) {
        return therapyProgramDAO.getProgramIdByName(selectedProgramName);
    }

    @Override
    public String getProgramNameById(String programId) {
        return therapyProgramDAO.getProgramNameById(programId);
    }

    @Override
    public List<String> getRegisteredProgramsByPatientId(String patientId) {
        return therapyProgramDAO.getRegisteredProgramsByPatientId(patientId);
    }

    @Override
    public double getProgramFeeById(String programId) {
        return therapyProgramDAO.getProgramFeeById(programId);
    }
}
