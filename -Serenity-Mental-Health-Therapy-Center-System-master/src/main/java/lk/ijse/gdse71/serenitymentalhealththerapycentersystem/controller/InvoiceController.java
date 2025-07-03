package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    private AnchorPane invoiceAnchorPane;

    @FXML
    private Label lblAdvancePayment;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPatientId;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblProgramFee;

    @FXML
    private Label lblProgramId;

    @FXML
    private Label lblRemainingAmount;

    @FXML
    private Label lblSessionAmount;

    @FXML
    private Label lblSessionCount;

    @FXML
    private Label lblTotalSessionFee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
