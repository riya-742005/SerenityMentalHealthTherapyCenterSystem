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
@Table(name = "therapy_programs")
public class TherapyProgram {
    @Id
    private String id;

    private String programName;
    private String duration;
    private double fee;

    /*@OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL)
    private List<Therapist> therapists;

    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions;*/
}
