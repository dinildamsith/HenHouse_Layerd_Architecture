package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.CustomerBO;
import lk.ijse.henHouse.bo.custom.EmployeeBO;
import lk.ijse.henHouse.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.henHouse.bo.custom.impl.EmployeeUniqMethodBOImpl;
import lk.ijse.henHouse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.EmployeeUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.EmployeeDTO;
import lk.ijse.henHouse.dto.tm.EmployeeTm;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFormController  {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXComboBox<String> CmbJob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtGmail;

    @FXML
    private JFXButton btnnewid;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXButton btnsave;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableColumn colAddress;


    @FXML
    private Label lblMain;
    @FXML
    private TableColumn colGmail;

    @FXML
    private TableColumn colContact;

    @FXML
    private TextField txtsearchid;

    @FXML
    private JFXButton btnsearch;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    private EmployeeTm selectedEmployee;

    public void initialize() throws ClassNotFoundException {
        String[] type = {"Owner","Maneger","Admin","User","Shop Worker"};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        CmbJob.setItems(list);
        addTableListener();

        colId.setCellValueFactory(new PropertyValueFactory<EmployeeTm, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<EmployeeTm, String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<EmployeeTm, String >("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<EmployeeTm, String>("contact"));
        colGmail.setCellValueFactory(new PropertyValueFactory<EmployeeTm, String>("gmail"));
        colSalary.setCellValueFactory(new PropertyValueFactory<EmployeeTm, Double>("salary"));

        initUI();

        try {
            EmployeeBOImpl employeeBO = new EmployeeBOImpl();
            ArrayList<EmployeeDTO> all = employeeBO.getAllEmployee();
            List<EmployeeTm> employeeTmList = all.stream().map(employee ->
                    new EmployeeTm(employee.getId(), employee.getName(), employee.getSalary(), employee.getAddress(),employee.getGmail(), employee.getContact())).collect(Collectors.toList());
            tblEmployee.setItems(FXCollections.observableArrayList(employeeTmList));
            tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
                btnsave.setText(curr != null ? "Update" : "Save");
                btnsave.setDisable(curr == null);

                if (curr != null) {
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void addTableListener() {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null){
                lblMain.setText("Update Customer Details");
                btnsave.setText("Update Details");
                btnsave.setStyle("-fx-background-color: #f48c06");
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtContact.setText(newValue.getContact());
                txtGmail.setText(newValue.getGmail());
                txtAddress.setText(newValue.getAddress());
            }
        });
    }

    @FXML
    void NewEmployeeIdOnAction(ActionEvent event) {
        selectedEmployee = null;
        init();
        clearall();
        String newEmployeeId = null;
        try {
            newEmployeeId = employeeBO.generateNewEmployeeID();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtId.setText(newEmployeeId);
    }

    @FXML
    void SaveEmployeeOnAction(ActionEvent event) throws ClassNotFoundException {
        check();
        if (btnsave.getText().equalsIgnoreCase("Save")) {
            /*Save Customer*/
            try {
                EmployeeDTO employee = makeObject();
                //Add Customer
                boolean addemployee =employeeBO.addEmployee(employee);
                tblEmployee.getItems().add(new EmployeeTm(employee.getId(), employee.getName(), employee.getSalary(), employee.getAddress(), employee.getGmail(), employee.getContact()));
                if(addemployee){
                    new Alert(Alert.AlertType.CONFIRMATION,"Employee Added Sucessfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something Error").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the employee " + e.getMessage()).show();
            }


        } else {
            /*Update customer*/
            try {
                //Update Customer
                EmployeeDTO employee = new EmployeeDTO();
                String emplId= txtId.getText();
                if (employeeBO.existEmployee(employee.getId())){
                    boolean updateEmployee = employeeBO.updateEmployee(new EmployeeDTO(employee.getId(),employee.getName(),employee.getContact(),employee.getJob(),employee.getGmail(),employee.getSalary(),employee.getAddress()));
                    if (updateEmployee){
                        new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated Sucessfully").show();
                    }else {

                        new Alert(Alert.AlertType.ERROR,"Something Error").show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"You Cannot Update ID").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to update the employee " +e.getMessage()).show();
            }
            tblEmployee.refresh();
        }

    }

    @FXML
    void employeeDetailOnMouseClicked(MouseEvent event) throws SQLException {
        init();
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null){
                lblMain.setText("Update Customer Details");
                btnsave.setText("Update Details");
                btnsave.setStyle("-fx-background-color: #f48c06");
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                txtGmail.setText(newValue.getGmail());
                txtAddress.setText(newValue.getAddress());
            }
        });
    }

    @FXML
    void searchEmployeeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        EmployeeUniqMethodBOImpl employeeUniqMethodBO = new EmployeeUniqMethodBOImpl();
        String txt = txtsearchid.getText();
        EmployeeDTO employee = employeeUniqMethodBO.searchEmployeeByText(txt);
        tblEmployee.getItems().clear();
        tblEmployee.getItems().add(new EmployeeTm(employee.getId(), employee.getName(), employee.getSalary(), employee.getAddress(),
                employee.getGmail(), employee.getContact()));
    }

    private EmployeeDTO makeObject(){
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String gmail = txtGmail.getText();
        String address = txtAddress.getText();
        String job = CmbJob.getSelectionModel().getSelectedItem().toString();
        double salary =Double.parseDouble(txtSalary.getText());
        return new EmployeeDTO(id,name,contact,job,gmail,salary,address);
    }

    private void check(){
        if (!txtName.getText().matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!txtAddress.getText().matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        } else if (!txtContact.getText().matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtContact.requestFocus();
            return;
        }else if (!txtGmail.getText().matches("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email input").show();
            txtGmail.requestFocus();
            return;
        }else if (!txtSalary.getText().matches("[0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary input").show();
            txtSalary.requestFocus();
            return;
        }
    }

    private void initUI() {
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtSalary.setDisable(true);
        txtGmail.setDisable(true);
        CmbJob.setDisable(true);
        txtContact.setDisable(true);
        txtId.setEditable(false);
        btnsave.setDisable(true);
    }

    private void init(){
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtSalary.setDisable(false);
        txtGmail.setDisable(false);
        CmbJob.setDisable(false);
        txtContact.setDisable(false);
        txtId.setEditable(false);
        txtName.setEditable(true);
        txtAddress.setEditable(true);
        txtSalary.setEditable(true);
        txtGmail.setEditable(true);
        CmbJob.setEditable(true);
        txtContact.setEditable(true);
        btnsave.setDisable(false);
    }

    private void clearall(){
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtGmail.clear();
        txtAddress.clear();
        txtSalary.clear();
        CmbJob.setPromptText("Job Type");

    }
}
