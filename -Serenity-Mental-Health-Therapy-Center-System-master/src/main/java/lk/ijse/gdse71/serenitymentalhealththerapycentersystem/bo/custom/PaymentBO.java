package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    String getNextPaymentId();

    ArrayList<PaymentDTO> getAllPayments();

    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);

    boolean paymentUpdate(PaymentDTO paymentDTO);

    boolean deletePayment(String paymentId);

    List<PaymentDTO> searchPayment(String searchText);
}
