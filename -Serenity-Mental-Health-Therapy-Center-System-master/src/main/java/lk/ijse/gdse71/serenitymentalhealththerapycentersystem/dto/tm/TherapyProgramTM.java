package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapyProgramTM {
    private String programId;
    private String programName;
    private String duration;
    private double fee;
}
