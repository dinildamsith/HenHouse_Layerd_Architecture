package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane root1;

    @FXML
    private JFXButton manageSupplierBtn;


    @FXML
    private JFXButton dashboardBtn;

    @FXML
    private JFXButton manageOrderBtn;

    @FXML
    private JFXButton manageCustomerBtn;

    @FXML
    private JFXButton manageStockBtn;

    @FXML
    private JFXButton manageEmployeeBtn;

    private AnchorPane anchorPane;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setForms("/view/DashBoardLoader.fxml");
    }

    @FXML
    void dashBoardBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/DashBoardLoader.fxml");
    }

    @FXML
    void manageOrderBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/OrderForm.fxml");
    }

    @FXML
    void manageCustomerBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/CustomerForm.fxml");
    }
    @FXML
    public void ManageSupplierBtnOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/SupplierForm.fxml");
    }
    @FXML
    public void manageStockBtnOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/StockForm.fxml");
    }
    @FXML
    public void manageEmployeeBtnOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/EmployeeForm.fxml");
    }

    public void setForms(String forms) throws IOException {

        String[] formsArray = {"/view/EmployeeForm.fxml", "/view/StockForm.fxml", "/view/SupplierForm.fxml", "/view/CustomerForm.fxml", "/view/OrderForm.fxml", "/view/DashBoardLoader.fxml"};

        JFXButton[] btnArray = {manageEmployeeBtn, manageStockBtn, manageSupplierBtn, manageCustomerBtn, manageOrderBtn, dashboardBtn};

        anchorPane = FXMLLoader.load(getClass().getResource(forms));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);

        for (int i = 0; i < formsArray.length; i++) {

            btnArray[i].setStyle("-fx-background-color: linear-gradient(to bottom, #ff0000 39%, #990033 100%)");

            if (forms.equals(formsArray[i])) {
                btnArray[i].setStyle("-fx-background-color: #6a040f");
            }
        }
    }


    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane1 = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane1));
        Stage stage1 = (Stage) root1.getScene().getWindow();
        stage1.close();
        stage.show();
    }
}
