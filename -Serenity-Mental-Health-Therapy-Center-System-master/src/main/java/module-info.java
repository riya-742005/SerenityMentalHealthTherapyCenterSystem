module lk.ijse.gdse71.serenitymentalhealththerapycentersystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires lombok;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.desktop;
    requires jbcrypt;

    opens lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity to org.hibernate.orm.core;
    opens lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config to jakarta.persistence;

    opens lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm to javafx.base;
    opens lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller to javafx.fxml;
    exports lk.ijse.gdse71.serenitymentalhealththerapycentersystem;

}