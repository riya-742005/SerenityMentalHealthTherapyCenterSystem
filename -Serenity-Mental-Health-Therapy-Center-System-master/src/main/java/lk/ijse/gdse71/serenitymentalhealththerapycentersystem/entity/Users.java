package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class Users {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
}
