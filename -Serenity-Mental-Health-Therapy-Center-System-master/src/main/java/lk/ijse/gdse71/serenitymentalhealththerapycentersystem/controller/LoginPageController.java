package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.BOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.UserBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Users;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private Button btnForgetPw;

    @FXML
    private Button btnLogin;

    @FXML
    private ToggleButton btnShowPw;

    @FXML
    private Text lblLoginPageTopic;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblShown;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane loginPageAnchorPane;

    @FXML
    private VBox loginVBox;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    DashboardController dashboardController = new DashboardController();


    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText().trim();
        String pw = txtPassword.getText().trim();

        if (userName.isEmpty() || pw.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter both username and password").show();
            return;
        }
        try {
            Users users = userBO.findByUserName(userName);
            if (users == null) {
                new Alert(Alert.AlertType.INFORMATION, "User name not found. Please enter correct user-name or signup first ").show();
            } else {
                String hashedPw = users.getPassword();
                if (BCrypt.checkpw(pw, hashedPw)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                    AnchorPane pane = loader.load();

                    DashboardController dashboardController = loader.getController();
                    dashboardController.setUserRole(users.getRole());
                    System.out.println("user role : " + users.getRole());

                    loginPageAnchorPane.getChildren().clear();
                    loginPageAnchorPane.getChildren().add(pane);

                } else {
                    new Alert(Alert.AlertType.ERROR, "Incorrect password! Please try again").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error ocuured while logging in.").show();
        }
    }

    @FXML
    void passwordKeyFieldType(KeyEvent event) {
        lblShown.textProperty().bind(Bindings.concat(txtPassword.getText()));
    }


    @FXML
    void clickBtnShowPassword(ActionEvent event) {
        if (btnShowPw.isSelected()) {
            lblShown.setVisible(true);
            lblShown.textProperty().bind(Bindings.concat(txtPassword.getText()));
            btnShowPw.setText("Hide");
        } else {
            lblShown.setVisible(false);
            btnShowPw.setText("Show");
        }
    }

    @FXML
    void forgetPwOnAction(ActionEvent event) {
        navigateTo("/view/forget-password.fxml");
    }

    public void navigateTo(String fxmlPath) {
        try {
            loginPageAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            loginPageAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblShown.setVisible(false);

        txtUserName.setOnAction(event -> txtPassword.requestFocus());
        txtPassword.setOnAction(this::btnLoginOnAction);
    }
}
