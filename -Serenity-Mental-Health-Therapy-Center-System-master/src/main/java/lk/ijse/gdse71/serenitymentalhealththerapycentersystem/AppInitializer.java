package lk.ijse.gdse71.serenitymentalhealththerapycentersystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/first-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280,726);
        stage.setTitle("");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FactoryConfiguration.getInstance().getSession();
        launch();
    }
}