package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class FirstPageController {

    @FXML
    private Button btnNext;

    @FXML
    private AnchorPane firstPageAnchorPane;

    @FXML
    private Text mainTopic;

    @FXML
    void ClickNextOnAction(ActionEvent event) {
        navigateTo("/view/login-page.fxml");
    }

    public void navigateTo(String fxmlPath) {
        try {
            firstPageAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            firstPageAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
