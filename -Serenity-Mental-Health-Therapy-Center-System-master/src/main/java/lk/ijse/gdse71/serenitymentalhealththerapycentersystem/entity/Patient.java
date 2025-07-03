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
@Table(name = "patient")
public class Patient {
    @Id
    private String id;

    private String name;
    private String address;
    private String email;
    private int mobileNumber;
    private String nic;
    private String gender;

   /* @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions;*/
}
