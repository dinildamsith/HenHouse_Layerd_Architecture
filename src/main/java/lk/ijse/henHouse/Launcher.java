package lk.ijse.henHouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(FXMLLoader.<Parent>load(getClass().getResource("/view/loadingForm.fxml"))));
        stage.show();
    }

}
