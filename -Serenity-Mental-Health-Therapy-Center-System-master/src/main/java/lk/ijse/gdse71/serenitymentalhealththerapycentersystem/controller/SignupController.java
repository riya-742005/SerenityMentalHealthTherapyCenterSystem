package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.BOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.UserBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblConfirmPW;

    @FXML
    private Label signupLblEmail;

    @FXML
    private Label lblFName;

    @FXML
    private Label lblLName;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblTopicSignup;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserId;

    @FXML
    private RadioButton radioBtnAdmin;

    @FXML
    private RadioButton rdBtnReceptionist;

    @FXML
    private AnchorPane signupAnchorPane;

    @FXML
    private VBox signupPageVBox;

    @FXML
    private TextField txtConfirmPW;

    @FXML
    private TextField txtSignupEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtPW;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void selectAdmin(ActionEvent event) {
        rdBtnReceptionist.setSelected(false);
    }

    @FXML
    void selectReceptionist(ActionEvent event) {
        radioBtnAdmin.setSelected(false);
    }

    @FXML
    void signupOnAction(ActionEvent event) {
        String id = lblUserId.getText();
        String fName = txtFName.getText();
        String lName = txtLName.getText();
        String email = txtSignupEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPW.getText();
        String confirmPW = txtConfirmPW.getText();
        String role = radioBtnAdmin.isSelected() ? "Admin" : "Receptionist";

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";


        if (fName.isEmpty() || !fName.matches(namePattern)) {
            txtFName.setStyle(errorStyle);
            errorMessage.append("- First name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtFName.setStyle(defaultStyle);
        }
        if (lName.isEmpty() || !lName.matches(namePattern)) {
            txtLName.setStyle(errorStyle);
            errorMessage.append("- Last name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtLName.setStyle(defaultStyle);
        }

        if (email.isEmpty() || !email.matches(emailPattern)) {
            txtSignupEmail.setStyle(errorStyle);
            errorMessage.append("- Email is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtSignupEmail.setStyle(defaultStyle);
        }

        if (userName.isEmpty()) {
            txtUserName.setStyle(errorStyle);
            errorMessage.append("- User name is empty\n");
            hasErrors = true;

        }else{
            txtUserName.setStyle(defaultStyle);
        }
        if (password.isEmpty()) {
            txtPW.setStyle(errorStyle);
            errorMessage.append("- Password field is empty\n");
            hasErrors = true;

        }else{
            txtPW.setStyle(defaultStyle);
        }
        if (confirmPW.isEmpty()) {
            txtConfirmPW.setStyle(errorStyle);
            errorMessage.append("- Confirm password field is empty\n");
            hasErrors = true;

        }else{
            txtConfirmPW.setStyle(defaultStyle);
        }

        if(!password.equals(confirmPW)){
            txtConfirmPW.setStyle(errorStyle);
            errorMessage.append("- Confirm password does not match to the password\n");
            hasErrors = true;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());

        boolean isSaved = userBO.saveUser(new UsersDTO(id,fName,lName,email,userName,hashedPassword,role));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User signup successfully!").show();
            navigateTo("/view/login-page.fxml");
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed").show();
        }

    }

    public void navigateTo(String fxmlPath) {
        try {
            signupAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            signupAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        txtFName.setStyle(defaultStyle);
        txtLName.setStyle(defaultStyle);
        txtSignupEmail.setStyle(defaultStyle);
        txtUserName.setStyle(defaultStyle);
        txtPW.setStyle(defaultStyle);
        txtConfirmPW.setStyle(defaultStyle);

        try{
            refreshPage();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load users id").show();
        }
    }

    private void refreshPage() {
        loadNextUserId();

        txtFName.setText("");
        txtLName.setText("");
        txtSignupEmail.setText("");
        txtUserName.setText("");
        txtPW.setText("");
        txtConfirmPW.setText("");
        rdBtnReceptionist.setSelected(false);
        radioBtnAdmin.setSelected(false);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        txtFName.setStyle(defaultStyle);
        txtLName.setStyle(defaultStyle);
        txtSignupEmail.setStyle(defaultStyle);
        txtUserName.setStyle(defaultStyle);
        txtPW.setStyle(defaultStyle);
        txtConfirmPW.setStyle(defaultStyle);
    }

    private void loadNextUserId() {
        String nextUserId = userBO.getNextUserId();
        lblUserId.setText(nextUserId);
    }
}
