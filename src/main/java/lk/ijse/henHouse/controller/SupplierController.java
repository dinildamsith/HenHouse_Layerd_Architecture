package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.SupplierBO;
import lk.ijse.henHouse.bo.custom.impl.SupplierBOImpl;
import lk.ijse.henHouse.bo.custom.impl.SupplyBOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.henHouse.dto.Supplier;
import lk.ijse.henHouse.dto.tm.CustomerTM;
import lk.ijse.henHouse.dto.tm.SupplierTm;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierController {
    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtcontact;

    @FXML
    private JFXTextField txtdesc;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtgmail;

    @FXML
    private JFXButton newId;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXButton btnsave;

    @FXML
    private TableView<SupplierTm> tblsupplier;

    @FXML
    private TableColumn<SupplierTm, String> colsupid;

    @FXML
    private TableColumn<SupplierTm, String> colname;

    @FXML
    private TableColumn<SupplierTm, String> coldesc;

    @FXML
    private TableColumn<SupplierTm, String> coladdress;

    @FXML
    private TableColumn<SupplierTm, String> colgmail;

    @FXML
    private TableColumn<SupplierTm, String> colcontact;

    @FXML
    private TextField txtsearch;

    @FXML
    private Label lblMain;

    @FXML
    private JFXButton btnsearch;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);


    private SupplierTm selectedSupplier;

    public void initialize() throws ClassNotFoundException {
        colsupid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colgmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        initUI();

        try {
            ArrayList<Supplier> all = supplierBO.getAllSuppliers();
            List<SupplierTm> supplierTmList = all.stream().map(supplier ->
                    new SupplierTm(supplier.getId(), supplier.getName(), supplier.getDesc(), supplier.getAddress(),supplier.getEmail(),supplier.getContact())).collect(Collectors.toList());
            tblsupplier.setItems(FXCollections.observableArrayList(supplierTmList));
            tblsupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
                btnsave.setText(curr != null ? "Update" : "Save");
                btnsave.setDisable(curr == null);

                if (curr != null) {
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnsearch(ActionEvent event) throws SQLException {
        String id = txtsearch.getText();
        if (!txtsearch.getText().isEmpty()){
            try {
                ObservableList<SupplierTm> supplier = supplierBO.searchSupplier(id);
                if (!supplier.isEmpty()){
                    tblsupplier.setItems(supplier);
                }else {
                    new Alert(Alert.AlertType.WARNING,"No supplier found!").show();
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
    void savesupplierOnaction(ActionEvent event) throws ClassNotFoundException {
        check();
        if (btnsave.getText().equalsIgnoreCase("Save")) {
            /*Save Supplier*/
            try {
                Supplier supplier = makeObject();
                //Add Customer
                boolean addSupplier = supplierBO.saveSupplier(supplier);
                tblsupplier.getItems().add(new SupplierTm(supplier.getId(), supplier.getName(), supplier.getDesc(),
                        supplier.getAddress(),supplier.getEmail(),supplier.getContact()));
                if(addSupplier){
                    new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added Successfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something Error").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            }


        } else {
            /*Update customer*/
            try {
                //Update Customer
                Supplier supplier = makeObject();

                    boolean updateSupplier = supplierBO.updateSupplier(new Supplier(supplier.getId(),supplier.getName(),supplier.getDesc(),supplier.getAddress(),supplier.getEmail(),supplier.getContact()));
                    if (updateSupplier){
                        new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated Sucessfully").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Something Error").show();
                    }


            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Supplier " +e.getMessage()).show();
            }
            tblsupplier.refresh();
        }
    }

    @FXML
    void supplierdetailOnMouseClicked(MouseEvent event) throws SQLException {
        init();
        tblsupplier.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null){
                lblMain.setText("Update Customer Details");
                btnsave.setText("Update Details");
                btnsave.setStyle("-fx-background-color: #f48c06");
                txtsearch.setText(newValue.getId());
                txtid.setText(newValue.getId());
                txtname.setText(newValue.getName());
                txtdesc.setText(newValue.getDesc());
                txtaddress.setText(newValue.getAddress());
                txtgmail.setText(newValue.getEmail());
                txtcontact.setText(newValue.getContact());
            }
        });
    }
    private Supplier makeObject(){
        String id = txtid.getText();
        String name = txtname.getText();
        String contact = txtcontact.getText();
        String gmail = txtgmail.getText();
        String address = txtaddress.getText();
        String desc = txtdesc.getText();
        return new Supplier(id,name,desc,gmail,contact,address);
    }

    private void check(){
        if (!txtname.getText().matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtname.requestFocus();
            return;
        } else if (!txtaddress.getText().matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtaddress.requestFocus();
            return;
        } else if (!txtcontact.getText().matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtcontact.requestFocus();
            return;
        }else if (!txtgmail.getText().matches("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email input").show();
            txtgmail.requestFocus();
            return;
        }else if (!txtdesc.getText().matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary input").show();
            txtdesc.requestFocus();
            return;
        }
    }

    private void initUI() {
        txtid.setDisable(true);
        txtname.setDisable(true);
        txtaddress.setDisable(true);
        txtdesc.setDisable(true);
        txtgmail.setDisable(true);
        txtcontact.setDisable(true);
        txtid.setEditable(false);
        btnsave.setDisable(true);
    }

    private void init(){
        txtid.setDisable(false);
        txtname.setDisable(false);
        txtaddress.setDisable(false);
        txtcontact.setDisable(false);
        txtgmail.setDisable(false);
        txtdesc.setDisable(false);
        txtid.setEditable(false);
        txtcontact.setEditable(true);
        txtdesc.setEditable(true);
        txtgmail.setEditable(true);
        txtaddress.setEditable(true);
        txtname.setEditable(true);
        btnsave.setDisable(false);
    }

    @FXML
    void newIdOnAction(ActionEvent event) {
        selectedSupplier = null;
        init();
        clearAll();
        String newSupplierId = null;
        try {
            newSupplierId = supplierBO.generateNewSupplierID();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtid.setText(newSupplierId);
    }

    private void clearAll() {
        txtid.clear();
        txtdesc.clear();
        txtcontact.clear();
        txtgmail.clear();
        txtaddress.clear();
        txtname.clear();
    }

}

