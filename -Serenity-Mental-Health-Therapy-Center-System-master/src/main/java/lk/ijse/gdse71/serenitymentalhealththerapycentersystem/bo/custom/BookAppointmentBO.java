package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;

public interface BookAppointmentBO extends SuperBO {

    boolean saveSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO);
}
