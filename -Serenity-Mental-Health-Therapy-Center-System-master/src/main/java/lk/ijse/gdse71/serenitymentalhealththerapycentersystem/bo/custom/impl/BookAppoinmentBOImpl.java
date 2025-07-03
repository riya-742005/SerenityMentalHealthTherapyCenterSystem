package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.BookAppointmentBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.*;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.*;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class BookAppoinmentBOImpl implements BookAppointmentBO {
    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPYSESSION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public boolean saveSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(therapySessionDTO.getId());
        System.out.println(paymentDTO.getId());

        try{
            String programId = therapySessionDTO.getProgramId();

            Optional<TherapyProgram> optionalTherapyProgram = therapyProgramDAO.findByPK(programId);
            if(optionalTherapyProgram.isEmpty()){
                transaction.rollback();
                return false;
            }

            String patientId = therapySessionDTO.getPatientId();

            Optional<Patient> optionalPatient = patientDAO.findByPK(patientId);
            if(optionalPatient.isEmpty()){
                transaction.rollback();
                return false;
            }

            String therapistId = therapySessionDTO.getTherapistId();

            Optional<Therapist> optionalTherapist = therapistDAO.findByPK(therapistId);
            if(optionalTherapist.isEmpty()){
                transaction.rollback();
                return false;
            }

            TherapySession therapySession = new TherapySession();
            therapySession.setId(therapySessionDTO.getId());
            therapySession.setDate(therapySessionDTO.getDate());
            therapySession.setSessionDate(therapySessionDTO.getSessionDate());
            therapySession.setDescription(therapySessionDTO.getDescription());
            therapySession.setTherapyProgram(optionalTherapyProgram.get());
            therapySession.setPatient(optionalPatient.get());
            therapySession.setTherapist(optionalTherapist.get());

          boolean isSessionSaved =  therapySessionDAO.saveSessionWithPayment(session , therapySession);
          if(!isSessionSaved){
              transaction.rollback();
              return false;
          }

            Payment payment = new Payment();
            payment.setId(paymentDTO.getId());
            payment.setDate(paymentDTO.getDate());
            payment.setAmount(paymentDTO.getAmount());
            payment.setStatus(paymentDTO.getStatus());
            payment.setTherapySession(therapySession);
            payment.setRemainingAmount(paymentDTO.getRemainingAmount());

            boolean isPaymentSaved = paymentDAO.savePayment(session , payment);
            if(!isPaymentSaved){
                transaction.rollback();
                return false;
            }

            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }



    }
}
