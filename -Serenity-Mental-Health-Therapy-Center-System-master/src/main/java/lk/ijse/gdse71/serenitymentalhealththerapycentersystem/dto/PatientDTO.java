package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private int mobileNumber;
    private String nic;
    private String gender;
}
