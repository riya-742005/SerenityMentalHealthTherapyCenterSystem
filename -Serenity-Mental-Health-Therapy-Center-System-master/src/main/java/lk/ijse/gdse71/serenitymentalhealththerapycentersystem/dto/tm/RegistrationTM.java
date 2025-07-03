package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationTM {
    private String registrationId;
    private String patientId;
    private String programId;
    private String patient;
    private String program;
    private double advancePayment;
    private LocalDate date;
}
