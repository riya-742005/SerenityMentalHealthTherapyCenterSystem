package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.SuperBO;

public interface HomeBO extends SuperBO {
    int getPatientCount();

    int getProgramCount();

    int getTherapistCount();

    int getSessionCountForToday();
}
