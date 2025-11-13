package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String code;
    private String description;
    private String category;
    private double unitPrice;
    private int qtyOnHand;
}
