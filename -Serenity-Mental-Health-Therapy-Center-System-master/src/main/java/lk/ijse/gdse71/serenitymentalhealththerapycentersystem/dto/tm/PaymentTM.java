package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTM {
    private String id;
    private String sessionId;
    private String patientName;
    private String program;
    private String description;
    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;
}
