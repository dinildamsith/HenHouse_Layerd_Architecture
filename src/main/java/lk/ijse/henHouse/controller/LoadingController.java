package lk.ijse.henHouse.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {
    @FXML
    private ProgressBar prgBar;
    @FXML
    private AnchorPane root;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 55);
                    Thread.sleep(55);
                }
                return null;
            }
        };

        prgBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {
            try {
                Stage stage1= (Stage) root.getScene().getWindow();
                stage1.close();
                Parent load = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(load));
                stage.setTitle("Login Page");
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        new Thread(task).start();


    }

}
