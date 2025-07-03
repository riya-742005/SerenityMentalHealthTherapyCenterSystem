package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Users;

public interface UserBO extends SuperBO {
    String getNextUserId();

    boolean saveUser(UsersDTO usersDTO);

    Users findByUserName(String userName);


}
