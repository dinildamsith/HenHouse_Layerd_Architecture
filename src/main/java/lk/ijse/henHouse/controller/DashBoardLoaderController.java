package lk.ijse.henHouse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.henHouse.bo.custom.impl.CustomerUniqMethodBOImpl;
import lk.ijse.henHouse.bo.custom.impl.EmployeeUniqMethodBOImpl;
import lk.ijse.henHouse.dao.custom.EmployeeUniqMethodDAO;
import lk.ijse.henHouse.dao.custom.impl.*;
import lk.ijse.henHouse.dto.tm.DashboardTM;

//import lk.ijse.henHouse.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardLoaderController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    void addNewAdminOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/addNewAdminForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }
    @FXML
    private Label lblEmployees;

    @FXML
    private Label lblCustomers;

    @FXML
    private Label lblOrders;

    @FXML
    private BarChart<String, Double> barchart;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<DashboardTM> tblItems;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;


    CustomerUniqMethodBOImpl customerUniqMethodBO = new CustomerUniqMethodBOImpl();
    EmployeeUniqMethodBOImpl employeeUniqMethodBO = new EmployeeUniqMethodBOImpl();
    ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    OrderImpl orderImpl = new OrderImpl();
    QuaryDAOImpl quaryDAOImpl = new QuaryDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        try {
            loadLabels();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setCellValues();
        populateTable();
        loadBarChart();
    }

    private void loadBarChart() {

        try {
            XYChart.Series<String,Double> series = quaryDAOImpl.getIncome();
            if (series != null){
                barchart.getData().addAll(series);
            }
            for (XYChart.Data<String, Double> data : series.getData()) {
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.7), data.getNode());
                tt.setFromY(400);
                tt.setToY(0);
                tt.play();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void setCellValues() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void populateTable() {
        try {
            ObservableList<DashboardTM> items = itemDAOImpl.getItemDetails();
            if (!items.isEmpty()){
                tblItems.setItems(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void loadLabels() throws ClassNotFoundException {
        try {
            String customer = customerUniqMethodBO.getCustomerCount();
            if (customer != null){
                lblCustomers.setText(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

        try {
            String orders = orderImpl.getOrderCount();
            if (orders != null){
                lblOrders.setText(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

        try {

            String employee = employeeUniqMethodBO.getEmployeeCount();
            if (employee != null){
                lblEmployees.setText(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
