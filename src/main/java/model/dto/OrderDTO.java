package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String code;
    private String description;
    private String category;
    private double unitPrice;
    private int qtyOnHand;
    private double totalPrice;
}
