package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDTO {
    private String id;
    private String programId;
    private String name;
    private String address;
    private int mobileNumber;
    private String nic;
}
