package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;
    private String sessionId;

}
