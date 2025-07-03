package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapist")
public class Therapist {
    @Id
    private String id;

    private String name;
    private String address;
    private int mobileNumber;
    private String nic;

   /* private String programId;*/

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

   /* @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions;*/
}
