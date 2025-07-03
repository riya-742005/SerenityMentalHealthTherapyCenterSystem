package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

    public UsersDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
