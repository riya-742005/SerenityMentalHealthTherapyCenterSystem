package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapySessionTM {
    private String sessionId;
    private LocalDate date;
    private String patientId;
    private String programId;
    private String therapistId;
    private String patient;
    private String program;
    private String therapist;
    private LocalDate sessionDate;
}
