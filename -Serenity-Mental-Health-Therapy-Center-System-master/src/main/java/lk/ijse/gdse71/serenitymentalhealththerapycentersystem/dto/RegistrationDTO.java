package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String id;
    private String patientId;
    private String programId;
    private LocalDate date;
    private double advancePayment;

}
