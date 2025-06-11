package ecommerce.example.ecommerce.dto;

//import ecommerce.example.ecommerce.model.Product;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}
