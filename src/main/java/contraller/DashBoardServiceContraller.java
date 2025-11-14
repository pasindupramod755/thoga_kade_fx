package contraller;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;
import model.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardServiceContraller {
    ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();
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
    public ObservableList<ItemDTO> addOrder(String code, int qty){
        if(searchDuplicate(code,qty)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
                preparedStatement.setString(1,code);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    itemList.add(
                            new ItemDTO(
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
        return itemList;
    }

    //Total price
    public Double getTotal(){
        orderPrice=0.0;
        for (ItemDTO itemDTO : itemList) {
            orderPrice+=itemDTO.getTotalPrice();
        }
        return orderPrice;
    }

    //Get Itom Qty
    public int getItemQty(){
        return itemList.size();
    }

    // Search duplicate
    public boolean searchDuplicate(String code, int qty) {
        for (ItemDTO item : itemList) {
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
    public ObservableList<ItemDTO> deleteOrder(String code){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getCode().equals(code)) {
                itemList.remove(i);
                i--;
            }
        }
        return itemList;
    }

    //search
    public ItemDTO searchItem(String code){
        for (ItemDTO item : itemList) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    //update order
    public ObservableList<ItemDTO> updateOrder(String code, int qty){
        ItemDTO itemDTO = searchItem(code);
        if (itemDTO != null) {
            itemDTO.setQtyOnHand(qty);
            itemDTO.setTotalPrice(itemDTO.getUnitPrice() * qty);
        }
        return itemList;
    }

    //searchOrderDatabase
    public ItemDTO searchItemDatabase(String code ){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            ItemDTO itemDTO = null;
            while (resultSet.next()){
                itemDTO = new ItemDTO(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("qtyOnHand"),
                        resultSet.getDouble("UnitPrice")
                );
            }
            return itemDTO;
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




}
