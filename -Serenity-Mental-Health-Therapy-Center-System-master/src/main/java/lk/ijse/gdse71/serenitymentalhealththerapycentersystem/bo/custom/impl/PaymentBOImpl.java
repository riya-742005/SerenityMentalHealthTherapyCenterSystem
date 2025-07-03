package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.PaymentBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PaymentDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapySessionDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.*;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPYSESSION);

    @Override
    public String getNextPaymentId() {
        return paymentDAO.getNextId();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOS.add(new PaymentDTO(
                    payment.getId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getRemainingAmount(),
                    payment.getStatus(),
                    payment.getTherapySession().getId()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId) {
        return paymentDAO.getPaymentsByPatientAndProgram(patientId,programId);
    }

    @Override
    public boolean paymentUpdate(PaymentDTO paymentDTO) {
        TherapySession  therapySession = therapySessionDAO.getSessionId(paymentDTO.getSessionId());

        if (therapySession == null) {
            throw new RuntimeException("Therapy Session not found ");
        }
        Payment payment = new Payment(
                paymentDTO.getId(),
                paymentDTO.getDate(),
                paymentDTO.getAmount(),
                paymentDTO.getRemainingAmount(),
                paymentDTO.getStatus(),
                therapySession
        );
        return paymentDAO.update(payment);
    }

    @Override
    public boolean deletePayment(String paymentId) {
        return paymentDAO.delete(paymentId);
    }

    @Override
    public List<PaymentDTO> searchPayment(String searchText) {
        List<Payment> payments = paymentDAO.search(searchText);
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOS.add(new PaymentDTO(
                    payment.getId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getRemainingAmount(),
                    payment.getStatus(),
                    payment.getTherapySession().getId()
            ));
        }
        return paymentDTOS;
    }
}
