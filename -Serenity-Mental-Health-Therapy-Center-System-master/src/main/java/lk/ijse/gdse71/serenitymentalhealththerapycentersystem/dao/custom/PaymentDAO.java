package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.CrudDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends CrudDAO<Payment> {
    boolean savePayment(Session session, Payment payment);

    Optional<Payment> findByPK(String paymentId);

    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);
}
