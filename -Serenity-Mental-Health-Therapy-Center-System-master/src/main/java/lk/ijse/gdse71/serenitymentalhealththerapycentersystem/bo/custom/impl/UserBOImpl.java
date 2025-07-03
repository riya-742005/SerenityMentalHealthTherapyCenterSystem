package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.UserBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.DAOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.UsersDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.UsersDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Users;

public class UserBOImpl implements UserBO {
    UsersDAO usersDAO = (UsersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getNextUserId() {
        return usersDAO.getNextId();
    }

    @Override
    public boolean saveUser(UsersDTO usersDTO) {
        return usersDAO.save(new Users(
               usersDTO.getId(),
                usersDTO.getFirstName(),
                usersDTO.getLastName(),
                usersDTO.getEmail(),
                usersDTO.getUsername(),
                usersDTO.getPassword(),
                usersDTO.getRole()
        ));
    }

    @Override
    public Users findByUserName(String userName) {
       return usersDAO.findByUserName(userName);

    }


}
