package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapyProgramDTO {
    private String id;
    private String programName;
    private String duration;
    private double fee;

    public TherapyProgramDTO(String id, String programName, int programDuration, int programFee) {
    }
}
