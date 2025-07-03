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
@Table(name = "payment")
public class Payment {
    @Id
    private String id;

    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;

    @OneToOne
    @JoinColumn(name = "session_id")
    private TherapySession therapySession;
}
