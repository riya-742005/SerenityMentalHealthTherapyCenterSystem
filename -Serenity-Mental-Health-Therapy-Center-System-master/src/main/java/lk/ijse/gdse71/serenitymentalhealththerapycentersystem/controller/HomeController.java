package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.BOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.HomeBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.HomeBOImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private PieChart lblPieChart;

    @FXML
    private Label mainTopic;

    HomeBO homeBO  = (HomeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.HOME);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPieChart();

    }

    private void setPieChart() {
        try{
            int patientCount = homeBO.getPatientCount();
            int programCount = homeBO.getProgramCount();
            int therapistCount = homeBO.getTherapistCount();
            int sessionCount = homeBO.getSessionCountForToday();

            PieChart.Data patientData = new PieChart.Data(("Patient Count : " + patientCount), patientCount);
            PieChart.Data programData = new PieChart.Data(("Program Count : " + programCount), programCount);
            PieChart.Data therapistData = new PieChart.Data(("Therapist Count : ") + therapistCount, therapistCount);
            PieChart.Data sessionData = new PieChart.Data(("Session Count for Today: ") + sessionCount, sessionCount);

            lblPieChart.getData().clear();
            lblPieChart.getData().addAll(patientData , programData , therapistData , sessionData);

            lblPieChart.applyCss();
            lblPieChart.lookup(".default-color0.chart-pie").setStyle("-fx-pie-color: #FF6347; ");

            lblPieChart.lookupAll(".chart-pie-label").forEach(label -> {
                label.setStyle("-fx-fill: white; -fx-font-size: 14px;");
            });

            ScaleTransition st = new ScaleTransition(Duration.millis(800), lblPieChart);
            st.setFromX(0);
            st.setFromY(0);
            st.setToX(1);
            st.setToY(1);
            st.play();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
