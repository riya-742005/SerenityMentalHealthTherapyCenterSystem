package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapySessionDTO {
    private String id;
    private String patientId;
    private String programId;
    private String therapistId;
    private String description;
    private LocalDate date;
    private LocalDate sessionDate;
}
