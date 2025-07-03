package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientTM {
    private String patientId;
    private String patientName;
    private String address;
    private String nic ;
    private String email;
    private int mobile;
    private String gender;
}
