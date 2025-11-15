package contraller;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.CustomerDTO;
import model.dto.ItemDTO;
import model.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardServiceContraller {
    ObservableList<OrderDTO> orderObservable = FXCollections.observableArrayList();
    ObservableList<ItemDTO> itemObservable = FXCollections.observableArrayList();
    ObservableList<CustomerDTO> customerObservable = FXCollections.observableArrayList();
    Double orderPrice = 0.0;

    Connection connection;
    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Order Contraller------------------------------------------------------------------------------------------------------->
    //Add Customer
    public ObservableList<OrderDTO> addOrder(String code, int qty){
        if(searchDuplicate(code,qty)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
                preparedStatement.setString(1,code);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    orderObservable.add(
                            new OrderDTO(
                                    resultSet.getString("ItemCode"),
                                    resultSet.getString("Description"),
                                    resultSet.getString("PackSize"),
                                    resultSet.getDouble("UnitPrice"),
                                    qty,
                                    resultSet.getDouble("UnitPrice")*qty
                            )
                    );

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return orderObservable;
    }

    //Total price
    public Double getTotal(){
        orderPrice=0.0;
        for (OrderDTO orderDTO : orderObservable) {
            orderPrice+= orderDTO.getTotalPrice();
        }
        return orderPrice;
    }

    //Get Itom Qty
    public int getItemQty(){
        return orderObservable.size();
    }

    // Search duplicate
    public boolean searchDuplicate(String code, int qty) {
        for (OrderDTO item : orderObservable) {
            if (item.getCode().equals(code)) {
                int newQty = item.getQtyOnHand() + qty;
                item.setQtyOnHand(newQty);
                double newPrice = item.getUnitPrice()*newQty;
                item.setTotalPrice(newPrice);
                return false;
            }
        }
        return true;
    }

    //delete order
    public ObservableList<OrderDTO> deleteOrder(String code){
        for (int i = 0; i < orderObservable.size(); i++) {
            if (orderObservable.get(i).getCode().equals(code)) {
                orderObservable.remove(i);
                i--;
            }
        }
        return orderObservable;
    }

    //search
    public OrderDTO searchItem(String code){
        for (OrderDTO item : orderObservable) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    //update order
    public ObservableList<OrderDTO> updateOrder(String code, int qty){
        OrderDTO orderDTO = searchItem(code);
        if (orderDTO != null) {
            orderDTO.setQtyOnHand(qty);
            orderDTO.setTotalPrice(orderDTO.getUnitPrice() * qty);
        }
        return orderObservable;
    }

    //searchOrderDatabase
    public OrderDTO searchItemDatabase(String code ){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderDTO orderDTO = null;
            while (resultSet.next()){
                orderDTO = new OrderDTO(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("qtyOnHand"),
                        resultSet.getDouble("UnitPrice")
                );
            }
            return orderDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //-------------------------------------------------------------------------------------------------------------->

    //Login Contraller --------------------------------------------------------------------------------------------->
    String[] usernameArray = {"pasindu", "tharindu", "lahiru", "navindu"};
    String[] passwordArray = {"12345", "23456", "34567", "45678"};
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < usernameArray.length; i++) {
            if (username.equals(usernameArray[i]) && password.equals(passwordArray[i])) {
                return true;
            }
        }
        return false;
    }
    //--------------------------------------------------------------------------------------------------------------->

    //--------------------------------------Customer Contraller------------------------------------------------------->

    public ObservableList<CustomerDTO> getAllCustomer() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
        ResultSet resultSet = preparedStatement.executeQuery();
        customerObservable.clear();
        while (resultSet.next()) {
            customerObservable.add(new CustomerDTO(
                    resultSet.getString("CustID"),
                    resultSet.getString("CustTitle"),
                    resultSet.getString("CustName"),
                    resultSet.getString("DOB"),
                    resultSet.getDouble("salary"),
                    resultSet.getString("CustAddress"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("PostalCode")
            ));
        }
        return customerObservable;
    }

    public boolean addCustomer(CustomerDTO customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getTitle());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(4, customer.getDob());
        preparedStatement.setDouble(5, customer.getSalary());
        preparedStatement.setString(6, customer.getAddress());
        preparedStatement.setString(7, customer.getCity());
        preparedStatement.setString(8, customer.getProvince());
        preparedStatement.setString(9, customer.getPostalCode());
        int i = preparedStatement.executeUpdate();
        if (i>0){
            customerObservable.add(customer);
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE CustID = ?");
        preparedStatement.setString(1,id);
        int i = preparedStatement.executeUpdate();
        if (i>0){
            for (CustomerDTO customerDTO : customerObservable){
                if ((customerDTO.getId()).equals(id)){
                    customerObservable.remove(customerDTO);
                }
            }
           return true;
        }
        return false;
    }

    public boolean updateCustomer(CustomerDTO customer) throws SQLException {
        String sql = "UPDATE Customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(9, customer.getId());
        preparedStatement.setString(1, customer.getTitle());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getDob());
        preparedStatement.setDouble(4, customer.getSalary());
        preparedStatement.setString(5, customer.getAddress());
        preparedStatement.setString(6, customer.getCity());
        preparedStatement.setString(7, customer.getProvince());
        preparedStatement.setString(8, customer.getPostalCode());
        int count = preparedStatement.executeUpdate();
        if(count>0){
            for (CustomerDTO customerDTO : customerObservable){
                if ((customerDTO.getId()).equals((customer.getId()))){
                    customerDTO.setTitle(customer.getTitle());
                    customerDTO.setName(customer.getName());
                    customerDTO.setDob(customer.getDob());
                    customerDTO.setSalary(customer.getSalary());
                    customerDTO.setAddress(customer.getAddress());
                    customerDTO.setCity(customer.getCity());
                    customerDTO.setProvince(customer.getProvince());
                    customerDTO.setPostalCode(customer.getPostalCode());
                }
            }
            return true;
        }
        return false;
    }

    //--------------------------------------------------------------------------------------------------------------->

    //----------------------------------------Item Contraller-------------------------------------------------------->
    public ObservableList<ItemDTO> getAllItem() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet = preparedStatement.executeQuery();
        itemObservable.clear();
        while (resultSet.next()) {
            itemObservable.add(new ItemDTO(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Description"),
                    resultSet.getString("PackSize"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("QtyOnHand")
            ));
        }
        return itemObservable;
    }

    public void addItem(ItemDTO item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, item.getCode());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getCategory());
            preparedStatement.setInt(4, item.getQtyOnHand());
            preparedStatement.setDouble(5, item.getUnitPrice());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Added successfully!").show();
                itemObservable.add(item);
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }


    public void deleteItem(ItemDTO item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Item WHERE ItemCode = ?");
            preparedStatement.setString(1, item.getCode());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted successfully!").show();
                for (ItemDTO itemDTO : itemObservable){
                    if ((itemDTO.getCode()).equals((item.getCode()))){
                        itemObservable.remove(itemDTO);
                    }
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
}
