package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField password;

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
       // try {
            //if (email.getText()!=null && password.getText()!=null && UserModel.checkPasswordAndUserName(email.getText(),password.getText())) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(anchorPane));
                Stage stage1 = (Stage) root.getScene().getWindow();
                stage1.close();
                stage.setTitle("Dashboard");
                stage.centerOnScreen();
                stage.show();
            //}else{
              //  new Alert(Alert.AlertType.WARNING, "Please fill all details or Incorrect Credentials").show();
        //    }
       // } catch (SQLException e) {
           // e.printStackTrace();
       // }
    }
}
