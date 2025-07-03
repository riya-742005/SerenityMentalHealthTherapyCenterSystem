package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapistTM {
   private String therapistId;
   private String name;
   private String address;
   private String nic;
   private int mobile;
   private String program;
   private String programId;
}
