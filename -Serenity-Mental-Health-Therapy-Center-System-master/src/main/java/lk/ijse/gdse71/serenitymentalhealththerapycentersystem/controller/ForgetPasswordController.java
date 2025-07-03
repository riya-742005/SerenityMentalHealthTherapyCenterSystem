package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ForgetPasswordController {

    @FXML
    private AnchorPane forgetPwFirstPageAnchorPane;

    @FXML
    private Label lblEmail;

    @FXML
    private Button nextBtn;

    @FXML
    private TextField txtEmail;

    @FXML
    void nextButtonOnaction(ActionEvent event) {

    }

    public void navigateTo(String fxmlPath) {
        try {
            forgetPwFirstPageAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            forgetPwFirstPageAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
