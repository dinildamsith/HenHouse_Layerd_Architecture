package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.*;
import lk.ijse.henHouse.bo.custom.impl.SupplierDetailsBOImpl;
import lk.ijse.henHouse.bo.custom.impl.SupplyBOImpl;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.SupplyDAO;
import lk.ijse.henHouse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplierUniqMethodDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplyDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplyUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.ItemDTO;
import lk.ijse.henHouse.dto.SupplierDTO;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;


import lk.ijse.henHouse.util.RegExPatterns;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockFormController implements Initializable {

    @FXML
    private JFXTextField descriptionTxt;

    @FXML
    private JFXTextField qtyOnHandTxt;

    @FXML
    private JFXTextField qtyTxt;

    @FXML
    private JFXTextField manufacturedDateTxt;

    @FXML
    private JFXTextField unitPriceTxt;

    @FXML
    private JFXTextField expirationDateTxt;

    @FXML
    private JFXComboBox<String> itemComboBox;

    @FXML
    private JFXTextField SupNameTxt;

    @FXML
    private JFXTextField SupCompanyTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXComboBox<String> supIdComboBox;

    @FXML
    private TableView<SupplyTM> supplyTbl;

    @FXML
    private TableColumn<SupplyTM,String> colStockId;

    @FXML
    private TableColumn<SupplyTM,String> colItemCode;

    @FXML
    private TableColumn<SupplyTM,String> colSupplyId;

    @FXML
    private TableColumn<SupplyTM,Integer> colQty;

    @FXML
    private TableColumn<SupplyTM,Double> colUnitPrice;

    @FXML
    private TableColumn<SupplyTM,LocalDate> colExpiringDate;

    @FXML
    private TextField stockIdSearchTxt;

    @FXML
    private JFXButton searchBtn;

    private ObservableList<SupplyTM> obList=null;

    private int qty=0;

    private String stockId=null;

    SupplierDetailBO suppliersDetailsBO = (SupplierDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER_DETAILS);
    SupplierUniqMethodBO supplierUniqMethodBO = (SupplierUniqMethodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER_UNIQ);
    SupplyUniqMethodBO supplyUniqMethodBO = (SupplyUniqMethodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLY_UNIQ);
    SupplyDAO supplyDAOImpl = (SupplyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLY);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

   // ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    //SupplyBOImpl supplyBO = new SupplyBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllSupplierIds();
        loadAllItemIds();
        setCellValueFactory();
        try {
            setTableData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setTableData() throws ClassNotFoundException {
        ArrayList<SupplyTM> supplyList=null;
        try {
            supplyList=supplyDAOImpl.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (SupplyTM supplyDto:supplyList) {
            obList.add(supplyDto);
        }

        supplyTbl.setItems(obList);
    }

    private void setCellValueFactory() {

        colStockId.setCellValueFactory(new PropertyValueFactory<>("StockId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colSupplyId.setCellValueFactory(new PropertyValueFactory<>("supplyId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colExpiringDate.setCellValueFactory(new PropertyValueFactory<>("expiringDate"));

        obList = FXCollections.observableArrayList();
        supplyTbl.setItems(obList);
    }

    private void loadAllItemIds() {
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<String> itemIdList=new ArrayList<>();
        try {
            itemIdList= itemBO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String itemId:itemIdList) {
            data.add(itemId);
        }

        itemComboBox.setItems(data);
    }

    private void loadAllSupplierIds() {

        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<String> supIdList=new ArrayList<>();
        try {
            supIdList= supplierUniqMethodBO.getAllSupplierDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String supId:supIdList) {
            data.add(supId);
        }

        supIdComboBox.setItems(data);

    }

    @FXML
    void onActionComboBox(ActionEvent event) throws SQLException {
        SupplierDTO supplierDTO=null;

        if (supIdComboBox.getValue()!=null){
           supplierDTO= supplierUniqMethodBO.getSupplierDetails(supIdComboBox.getValue());
           SupNameTxt.setText(supplierDTO.getSupName());
           SupCompanyTxt.setText(supplierDTO.getCompanyName());
        }

    }

    @FXML
    void onActionGetItem(ActionEvent event) {
        ItemDTO itemDTO =null;

        if (itemComboBox.getValue()!=null) {
            try {
                itemDTO = itemBO.getItemDetails(itemComboBox.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            descriptionTxt.setText(itemDTO.getDescription());
            qtyOnHandTxt.setText(itemDTO.getQty() + "");
        }
    }

    @FXML
    void onActionSave(ActionEvent event) throws ClassNotFoundException {

        boolean isSaved, isUpdated;
        if (itemComboBox.getValue() != null || supIdComboBox.getValue() != null || qtyTxt.getText() != null || unitPriceTxt.getText() != null || manufacturedDateTxt.getText() != null || expirationDateTxt.getText() != null) {
            if (RegExPatterns.getDoublePattern().matcher(unitPriceTxt.getText()).matches()) {
                if (RegExPatterns.getIntPattern().matcher(qtyTxt.getText()).matches()) {
                    if (RegExPatterns.getDatePattern().matcher(manufacturedDateTxt.getText()).matches()) {
                        if (RegExPatterns.getDatePattern().matcher(expirationDateTxt.getText()).matches()) {
                            try {

                                if (saveBtn.getText().equals("Save Details")) {
                                    isSaved = suppliersDetailsBO.save(new SupplyTM(itemComboBox.getValue(), supIdComboBox.getValue(), LocalDate.parse(manufacturedDateTxt.getText()), LocalDate.parse(expirationDateTxt.getText()), Double.parseDouble(unitPriceTxt.getText()), Integer.parseInt(qtyTxt.getText())));
                                    if (isSaved) {
                                        obList.add(new SupplyTM(supplyDAOImpl.generateNewID(),
                                                itemComboBox.getValue(), supIdComboBox.getValue(), Integer.parseInt(qtyTxt.getText()),
                                                Double.parseDouble(unitPriceTxt.getText()), LocalDate.parse(expirationDateTxt.getText())));

                                        new Alert(Alert.AlertType.INFORMATION, "Stock Details is saved.").showAndWait();
                                    }
                                } else {
                                    isUpdated = suppliersDetailsBO.update(new SupplyDTO(itemComboBox.getValue(),
                                            supIdComboBox.getValue(), LocalDate.parse(manufacturedDateTxt.getText()),
                                            LocalDate.parse(expirationDateTxt.getText()),
                                            Double.parseDouble(unitPriceTxt.getText()), Integer.parseInt(qtyTxt.getText())), qty, stockId);
                                    if (isUpdated) {
                                        obList.clear();
                                        try {
                                            setTableData();
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    new Alert(Alert.AlertType.ERROR, "Stock Details is updated.").showAndWait();
                                }
                            } catch (SQLException e) {
                                new Alert(Alert.AlertType.ERROR, "Stock Details is not saved.").showAndWait();
                                e.printStackTrace();
                            }
                            clearComponents();
                            saveBtn.setText("Save Details");
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Please enter correct date.").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Please enter correct date.").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Please enter correct qty").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please enter correct unit price").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please fill all details").show();
        }

    }

    private void clearComponents() {
        supIdComboBox.getSelectionModel().select(null);
        itemComboBox.getSelectionModel().select(null);
        SupNameTxt.setText(null);
        SupCompanyTxt.setText(null);
        expirationDateTxt.setText(null);
        manufacturedDateTxt.setText(null);
        unitPriceTxt.setText(null);
        qtyOnHandTxt.setText(null);
        qtyTxt.setText(null);
        descriptionTxt.setText(null);
    }

    @FXML
    void onActionSearch(ActionEvent event) {
        SupplyTM supplyDTOS=null;
        SupplierDTO supplierDTO=null;
        ItemDTO itemDTO =null;
        try {
            supplyDTOS=supplyUniqMethodBO.getSupplyDetails(stockIdSearchTxt.getText());
            supplierDTO= supplierUniqMethodBO.getSupplierDetails(supplyDTOS.getSupplyId());
            itemDTO = itemBO.getItemDetails(supplyDTOS.getItemCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stockId=stockIdSearchTxt.getText();
        supIdComboBox.getSelectionModel().select(supplyDTOS.getSupplyId());
        itemComboBox.getSelectionModel().select(supplyDTOS.getItemCode());
        SupNameTxt.setText(supplierDTO.getSupName());
        SupCompanyTxt.setText(supplierDTO.getCompanyName());
        expirationDateTxt.setText(supplyDTOS.getExpiringDate()+"");
        manufacturedDateTxt.setText(supplyDTOS.getManufacturingDate()+"");
        unitPriceTxt.setText(supplyDTOS.getUnitPrice()+"");
        qtyOnHandTxt.setText(String.valueOf(itemDTO.getQty()));
        qtyTxt.setText(String.valueOf(supplyDTOS.getQty()));
        descriptionTxt.setText(itemDTO.getDescription());
        stockIdSearchTxt.setText(null);
        saveBtn.setText("Update Details");
        qty=supplyDTOS.getQty();

    }

}
