package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.UserBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardAnchorPane;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private HBox navBar;

    @FXML
    private Label lblTopic;

    @FXML
    private Button navBarBtnPatient;

    @FXML
    private Button navAddUsersBtn;

    @FXML
    private Button btnHome;

    @FXML
    private Button navBarBtnPayment;

    @FXML
    private Button navBarBtnPrograms;

    @FXML
    private Button navBarBtnRegistration;

    @FXML
    private Button navBarBtnSession;

    @FXML
    private Button navBarBtnTherapist;

    @FXML
    private Button btnLogout;

    @FXML
    private Button navBarBtnUSer;

    @Setter
    private String userRole;


    @FXML
    void navigateToPamentPage(ActionEvent event) {
        lblTopic.setText("Payment History");
        navigateTo("/view/payment.fxml");
    }

    @FXML
    void navigateToPatientPage(ActionEvent event) {
        lblTopic.setText("Patient Management");
        navigateTo("/view/patient.fxml");
    }

    @FXML
    void btnAddUSers(ActionEvent event) {
        lblTopic.setText("Add Users");
        navigateTo("/view/signup-page.fxml");
    }


    @FXML
    void navigateToHomePage(ActionEvent event) {
        lblTopic.setText("");
        navigateTo("/view/home.fxml");
    }


    @FXML
    void navigateToProgramsPage(ActionEvent event) {
        lblTopic.setText("Program Management");
        navigateTo("/view/programs.fxml");
    }

    @FXML
    void navigateToRegistrationPage(ActionEvent event) {
        lblTopic.setText("Registration");
        navigateTo("/view/registration.fxml");
    }

    @FXML
    void navigateToSessionsPage(ActionEvent event) {
        lblTopic.setText("Booking Sessions");
        navigateTo("/view/session.fxml");
    }

    @FXML
    void navigateToTherapistPage(ActionEvent event) {
        lblTopic.setText("Therapist Management");
        navigateTo("/view/therapist.fxml");
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Logout from System?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            System.exit(0);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/home.fxml");
        lblTopic.setText("");


    }

    public void setUserRole(String userRole){
        this.userRole = userRole;

        if(userRole.equalsIgnoreCase("Receptionist")) {
            navBarBtnPrograms.setDisable(true);
            navBarBtnTherapist.setDisable(true);
            navBarBtnRegistration.setDisable(true);
            navAddUsersBtn.setDisable(true);
        }

    }

    public void navigateTo(String fxmlPath){
        try{
            homeAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            homeAnchorPane.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }


}
