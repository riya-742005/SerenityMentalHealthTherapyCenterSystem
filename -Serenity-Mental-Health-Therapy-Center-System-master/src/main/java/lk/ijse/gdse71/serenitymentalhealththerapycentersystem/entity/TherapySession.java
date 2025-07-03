package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_sessions")
public class TherapySession {
    @Id
    private String id;

    private String description;
    private LocalDate date;
    private LocalDate sessionDate;

    /*private String patientId;
    private String programId;
    private String therapistId;*/

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    /*@OneToOne(mappedBy = "therapySession", cascade = CascadeType.ALL)
    private Payment payment;*/
}
