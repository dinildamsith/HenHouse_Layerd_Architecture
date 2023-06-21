package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.EmployeeBO;
import lk.ijse.henHouse.bo.custom.EmployeeUniqMethodBO;
import lk.ijse.henHouse.bo.custom.UserBO;
import lk.ijse.henHouse.bo.custom.impl.EmployeeUniqMethodBOImpl;
import lk.ijse.henHouse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.UserDAOImpl;
import lk.ijse.henHouse.dto.EmployeeDTO;
import lk.ijse.henHouse.dto.User;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewAdminFormController implements Initializable {
    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtGmail;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeGmail;

    @FXML
    private JFXTextField txtEmployeeContact;

    @FXML
    private JFXButton signUpBtn;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    EmployeeUniqMethodBO employeeUniqMethodBO = (EmployeeUniqMethodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE_UNIQ);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    //UserDAOImpl userDAOImpl = new UserDAOImpl();

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws ClassNotFoundException {
        String employee_id = cmbEmployeeId.getSelectionModel().getSelectedItem();

        try {
            EmployeeDTO employee = employeeUniqMethodBO.searchEmployeeByText(employee_id);
            if (employee != null){
                txtEmployeeName.setText(employee.getName());
                txtEmployeeAddress.setText(employee.getAddress());
                txtEmployeeContact.setText(employee.getContact());
                txtEmployeeGmail.setText(employee.getGmail());
                txtGmail.setText(txtEmployeeGmail.getText());
                txtPassword.requestFocus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadEmployeeId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeId() throws ClassNotFoundException {
        try {
            List<String> ids = employeeBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for (String id: ids) {
                obList.add(id);
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {

        }

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        signUpBtn.fire();
    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) {
        String  id = cmbEmployeeId.getValue();
        String gmail = txtEmployeeGmail.getText();
        String password = txtPassword.getText();

        try {
            if (userBO.saveUser(new User(id,gmail,password))){
                new Alert(Alert.AlertType.CONFIRMATION,"Create new account!...").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
