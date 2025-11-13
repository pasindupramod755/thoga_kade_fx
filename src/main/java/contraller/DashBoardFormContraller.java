package contraller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.dto.ItemDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormContraller implements Initializable {

    String[] titleArray = {"Mr.","Mrs.","Miss"};
    DashBoardServiceContraller dashBoardService = new DashBoardServiceContraller();

    @FXML
    private Label OrdeTimeNow;

    @FXML
    private TextField OrderCustomerPayoutText;

    @FXML
    private Label OrderDateNow;

    @FXML
    private Label OrderItemText;

    @FXML
    private AnchorPane accountPane;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnOrder;

    @FXML
    private Label changeNameLabel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCategory1;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCode1;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colDescription1;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colOrderCode;

    @FXML
    private TableColumn<?, ?> colOrderName;

    @FXML
    private TableColumn<?, ?> colOrderPrice;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colPrice1;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colQty1;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label customerLabel;

    @FXML
    private AnchorPane customerPane;

    @FXML
    private Label employeeLabel;

    @FXML
    private Label itemLabel;

    @FXML
    private AnchorPane itemPane;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField orderCodeText;

    @FXML
    private Label orderCustomerNameLabel;

    @FXML
    private Label orderCustomerNameLabel1;

    @FXML
    private Label orderCustomerNameLabel11;

    @FXML
    private Label orderCustomerNameLabel111;

    @FXML
    private Label orderDiscountText;

    @FXML
    private TextField orderItemSearchBox;

    @FXML
    private TextField orderPriceText;

    @FXML
    private TextField orderQtyText;

    @FXML
    private Label orderSubTotalText;

    @FXML
    private Label supplierLabel;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    private TableView<ItemDTO> tblOrder;

    @FXML
    private TableColumn<?, ?> colOrderTotalPrice;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCategory1;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCode1;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDescription1;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPrice1;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtQty1;

    @FXML
    private TextField txtSalary;

    @FXML
    private ChoiceBox<?> txtTitle;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtpostalCode;

    @FXML
    private Label orderFinalTotalText;

    @FXML
    void OrderComfirmBtn(ActionEvent event) {

    }

    @FXML
    void btnCustomerAction(ActionEvent event) {
//        itemPane.setVisible(false);
//        customerPane.setVisible(true);
//        accountPane.setVisible(false);
//        btnCustomer.setStyle("-fx-background-color: #836fff; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
//        btnHome.setStyle("-fx-background-color: #ffffff15; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
//        btnItem.setStyle("-fx-background-color: #ffffff15; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
//        txtTitle.getItems().clear();
//        txtTitle.getItems().addAll(titleArray);
//        txtId.setText("");
//        txtName.setText("");
//        txtAddress.setText("");
//        txtProvince.setText("");
//        txtpostalCode.setText("");
//        txtSalary.setText("");
//        txtCity.setText("");
//        txtDate.setValue(null);
//        txtTitle.setValue(null);
//
//        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
//        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
//        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
//        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
//        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
//        tblCustomer.setItems(customerObservable);
//
//        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
//            txtId.setText(newValue.getId());
//            txtName.setText(newValue.getName());
//            txtAddress.setText(newValue.getAddress());
//            txtProvince.setText(newValue.getProvince());
//            txtpostalCode.setText(newValue.getPostalCode());
//            txtSalary.setText(String.valueOf(newValue.getSalary()));
//            txtCity.setText(newValue.getCity());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate localDate = LocalDate.parse(newValue.getDob(), formatter);
//            txtDate.setValue(localDate);
//            if (newValue.getTitle().equals("Mr.")){
//                txtTitle.setValue(txtTitle.getItems().get(0));
//            } else if (newValue.getTitle().equals("Mrs.")) {
//                txtTitle.setValue(txtTitle.getItems().get(1));
//            }else if (newValue.getTitle().equals("Miss.")){
//                txtTitle.setValue(txtTitle.getItems().get(2));
//            }
//        });
    }

    @FXML
    void btnCustomerAddAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerResetAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerUpdateAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

    }

    @FXML
    void btnItemAction(ActionEvent event) {

    }

    @FXML
    void btnItemAddAction(ActionEvent event) {

    }

    @FXML
    void btnItemDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnItemResetAction(ActionEvent event) {

    }

    @FXML
    void btnItemUpdateAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutAction(ActionEvent event) {

    }

    @FXML
    void btnLoginAction(ActionEvent event) {

    }

    @FXML
    void btnOrderAction(ActionEvent event) {

    }

    @FXML
    void btnpasswordAction(ActionEvent event) {

    }

    @FXML
    void btnuserName(ActionEvent event) {

    }

    @FXML
    void orderAddBtn(ActionEvent event) {
        ObservableList<ItemDTO> itemDTOS = dashBoardService.addCustomer(orderItemSearchBox.getText(), Integer.parseInt(orderQtyText.getText()));
        colOrderCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colOrderName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOrderTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tblOrder.setItems(itemDTOS);
        Double total = dashBoardService.getTotal();
        orderSubTotalText.setText("Rs."+total);
        orderDiscountText.setText("Rs."+total*0.2);
        orderFinalTotalText.setText("Rs."+(total-(total*0.2)));
        OrderItemText.setText(dashBoardService.getItemQty()+" Item");
        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue , newValue)->{
            orderCodeText.setText(newValue.getCode());
            orderPriceText.setText(String.valueOf(newValue.getUnitPrice()));
            orderQtyText.setText(colOrderQty.getText());

        });
        tblOrder.refresh();

    }

    @FXML
    void orderDeleteBtn(ActionEvent event) {

    }

    @FXML
    void orderItemSearchBoxBtn(ActionEvent event) {

    }

    @FXML
    void orderQtyTextAction(ActionEvent event) {

    }

    @FXML
    void orderSearchIconBtn(ActionEvent event) {

    }

    @FXML
    void orderUpdateBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
