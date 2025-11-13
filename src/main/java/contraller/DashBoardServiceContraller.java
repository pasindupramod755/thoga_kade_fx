package contraller;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardServiceContraller {
    ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();
    Double orderPrice = 0.0;

    Connection connection;
    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Add Customer
    public ObservableList<ItemDTO> addCustomer(String code,int qty){
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
            System.out.println(item.getCode());
            System.out.println(code);
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


}
