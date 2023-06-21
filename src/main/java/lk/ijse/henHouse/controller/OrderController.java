package lk.ijse.henHouse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.*;
import lk.ijse.henHouse.bo.custom.impl.CustomerUniqMethodBOImpl;
import lk.ijse.henHouse.bo.custom.impl.OrderPlaceBOImpl;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.CustomerDAO;
import lk.ijse.henHouse.dao.custom.CustomerUniqMethodDAO;
import lk.ijse.henHouse.dao.custom.ItemDAO;
import lk.ijse.henHouse.dao.custom.OrderDAO;
import lk.ijse.henHouse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.CustomerUniqMethodDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.OrderImpl;
import lk.ijse.henHouse.dto.CustomerDTO;
import lk.ijse.henHouse.dto.ItemDTO;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import lk.ijse.henHouse.dto.tm.OrderTM;


//import lk.ijse.henHouse.model.OrderModel;
//import lk.ijse.henHouse.model.PlaceOrderModel;
import lk.ijse.henHouse.util.RegExPatterns;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXComboBox<String> cmbCustomer;

    @FXML
    private JFXTextField txtCustomer;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXComboBox<String> cmbItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private TableView<OrderTM> tblCart;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXButton placeOrderBtn;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    CustomerUniqMethodBO customerUniqMethodBO= (CustomerUniqMethodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_UNIQ);

    ObservableList<OrderTM> cartItems = FXCollections.observableArrayList();

    @FXML
    void btnAddtoCartOnAction(ActionEvent event) {
        if (!(cmbItem.getSelectionModel().getSelectedItem() == null || txtQty.getText().isEmpty())){
            if (RegExPatterns.getIntPattern().matcher(txtQty.getText()).matches()) {
                String code = cmbItem.getSelectionModel().getSelectedItem();
                String desc = txtDescription.getText();
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
                int qty = Integer.parseInt(txtQty.getText());
                double total = unitPrice * qty;

                JFXButton button = new JFXButton("Remove");
                button.getStyleClass().add("removeBtn");
                button.setCursor(Cursor.HAND);
                setRemoveBtnOnAction(button);

                if (qty > qtyOnHand) {
                    new Alert(Alert.AlertType.WARNING, "Haven't enough items").show();
                    return;
                }

                if (!cartItems.isEmpty()) {
                    for (int i = 0; i < tblCart.getItems().size(); i++) {
                        if (colItemCode.getCellData(i).equals(code)) {
                            qty += (int) colQty.getCellData(i);
                            total = qty * unitPrice;

                            cartItems.get(i).setQty(qty);
                            cartItems.get(i).setTotal(total);

                            tblCart.refresh();
                            calculateNetTotal();
                            clearDetails();
                            return;
                        }
                    }
                }

                cartItems.add(new OrderTM(code, desc, qty, unitPrice, total, button));
                tblCart.setItems(cartItems);
                calculateNetTotal();
                clearDetails();
            }else {
                new Alert(Alert.AlertType.WARNING,"Invalid qty").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill the all details").show();
        }
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (!(cmbCustomer.getSelectionModel().getSelectedItem() == null || tblCart.getItems().isEmpty())){
            String orderId = txtOrderId.getText();
            String date = txtOrderDate.getText();
            String customerId = cmbCustomer.getSelectionModel().getSelectedItem();

            ArrayList<OrderDetailsDTO> items = new ArrayList<>();
            for (OrderTM cartItem: cartItems) {
                items.add(new OrderDetailsDTO(orderId,cartItem.getItemCode(),cartItem.getQty(),cartItem.getTotal(),cartItem.getUnitPrice()));
            }
            try {
                OrderPlaceBOImpl orderPlaceBO = new OrderPlaceBOImpl();
                boolean isPlaced  = orderPlaceBO.placeOrder(orderId,date,customerId,items);
                if (isPlaced){
                    new Alert(Alert.AlertType.CONFIRMATION,"Order Placed successfully").show();
                    generateOrderId();
                    cmbCustomer.getSelectionModel().clearSelection();
                    txtCustomer.clear();
                    txtContact.clear();
                    tblCart.getItems().clear();
                    calculateNetTotal();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Order Placed unsuccessfully").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill all details").show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = cmbCustomer.getSelectionModel().getSelectedItem();
        try {
            CustomerDTO customerDTO = customerUniqMethodBO.getCustomer(id);
            if (customerDTO != null) {
                txtCustomer.setText(customerDTO.getCustomerName());
                txtContact.setText(customerDTO.getContact());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String id = cmbItem.getSelectionModel().getSelectedItem();
        try {
            ItemDTO itemDTO = itemBO.getItem(id);
            if(itemDTO != null){
                txtDescription.setText(itemDTO.getDescription());
                txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(itemDTO.getQty()));
                txtQty.requestFocus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddtoCartOnAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        generateOrderId();
        setDate();
        try {
            loadCustomers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadItems();
    }

    private void clearDetails() {
        cmbItem.getSelectionModel().clearSelection();
        txtQty.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        cmbItem.requestFocus();
    }

    private void setRemoveBtnOnAction(JFXButton button) {
        button.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            int index = -1;
            if (result.orElse(no) == yes) {
                for (int i=0; i < tblCart.getItems().size(); i++) {
                    if (colAction.getCellData(i).equals(button)) {
                        index = i;
                    }
                }
                cartItems.remove(index);
                tblCart.refresh();
                calculateNetTotal();
            }
        });
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            double total  = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        txtTotal.setText(String.valueOf(netTotal));
    }

    private void loadItems() {
        try {
            ObservableList<String> items = itemBO.getItemIds();
            cmbItem.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void loadCustomers() throws ClassNotFoundException {
        try {
            ObservableList<String> customers = customerBO.getIds();
            cmbCustomer.setItems(customers);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void setDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtOrderDate.setText(format.format(date));
    }

    private void generateOrderId() {
        try {
            String id  = orderBO.generateOrderId();
            txtOrderId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void setCellValues() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
}
