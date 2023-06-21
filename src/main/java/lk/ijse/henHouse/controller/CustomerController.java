package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.CustomerBO;
import lk.ijse.henHouse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.henHouse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.henHouse.db.DBConnection;
import lk.ijse.henHouse.dto.CustomerDTO;
import lk.ijse.henHouse.dto.tm.CustomerTM;

import lk.ijse.henHouse.util.RegExPatterns;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private Label lblMain;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtTelephone;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtGmail;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colTelephone;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colGmail;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnSave;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        String customerId = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String contact = txtTelephone.getText();
        String gmail = txtGmail.getText();
        String address = txtAddress.getText();

        if (!(txtCustomerId.getText().isEmpty() ||txtCustomerName.getText().isEmpty() ||txtAddress.getText().isEmpty() ||txtGmail.getText().isEmpty() ||txtTelephone.getText().isEmpty())) {
            if (RegExPatterns.getCustomerId().matcher(customerId).matches()) {
                if (RegExPatterns.getNamePattern().matcher(name).matches()) {
                    if (RegExPatterns.getContactPattern().matcher(contact).matches()) {
                        if (RegExPatterns.getEmailPattern().matcher(gmail).matches()) {
                            if (btnSave.getText().equalsIgnoreCase("Save Details")) {
                                try {
                                    if (!customerBO.existCustomer(customerId)) {
                                        boolean isSaved = customerBO.addCustomer(new CustomerDTO(customerId, name, contact, gmail, address));
                                        if (isSaved) {
                                            new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                                           populateCustomerTable();
                                            clearTextField();
                                        } else {
                                            new Alert(Alert.AlertType.WARNING, "Customer not saved!").show();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "CustomerId is already exists").show();
                                    }
                                } catch (SQLException e) {
                                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                                }
                            } else {
                                try {
                                    if (customerBO.existCustomer(customerId)) {
                                        boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(customerId, name, contact,address, gmail));
                                        if (isUpdated) {
                                            new Alert(Alert.AlertType.CONFIRMATION, "Customer updated").show();
                                            //populateCustomerTable();
                                            clearTextField();
                                            generateNextId();
                                            lblMain.setText("Add Customer Details");
                                            btnSave.setText("Save Details");
                                            btnSave.setStyle("-fx-background-color: #b51313");
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "There is no such customer associated with the id").show();
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                                }
                            }
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Invalid gmail.").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Invalid contact number.").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Invalid name.").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Invalid customerId.").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill all details").show();
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();
        if (!txtSearch.getText().isEmpty()){
            try {
                ObservableList<CustomerTM> customer = customerBO.search(id);
                if (!customer.isEmpty()){
                    tblCustomer.setItems(customer);
                }else {
                    new Alert(Alert.AlertType.WARNING,"No customer found!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please Enter the ID").show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        //btnSearchOnAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        try {
            populateCustomerTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        addTableListener();
        try {
            generateNextId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTableListener() {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null){
                lblMain.setText("Update Customer Details");
                btnSave.setText("Update Details");
                btnSave.setStyle("-fx-background-color: #f48c06");
                txtCustomerId.setText(newValue.getCustomerId());
                txtCustomerName.setText(newValue.getCustomerName());
                txtTelephone.setText(newValue.getContact());
                txtGmail.setText(newValue.getGmail());
                txtAddress.setText(newValue.getAddress());
            }
        });
    }

    private void clearTextField(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtAddress.clear();
        txtGmail.clear();
        txtTelephone.clear();
    }

    private void generateNextId() throws ClassNotFoundException {
        try {
            String id = customerBO.generateNewCustomerID();
            txtCustomerId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void populateCustomerTable() throws ClassNotFoundException {
        try {
            ArrayList<CustomerDTO> customers = customerBO.getAllCustomers();

            for (CustomerDTO customerDto : customers){
                tblCustomer.getItems().add(new CustomerTM(customerDto.getCustomerId(),customerDto.getCustomerName(),customerDto.getContact(),customerDto.getAddress(),customerDto.getGmail()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void setCellValues() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
    }

    public void reportOnAction(ActionEvent actionEvent) throws SQLException, JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\SCS\\Downloads\\HenHouse 2\\HenHouse 2\\HenHouse\\HenHouse\\src\\main\\resources\\reports\\Customer.jrxml");
        String sql = "SELECT * FROM customer";

        JRDesignQuery updateQuary = new JRDesignQuery();
        updateQuary.setText(sql);

        jasperDesign.setQuery(updateQuary);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }
}
