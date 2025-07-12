package ecommerce.example.ecommerce.dto;

//import ecommerce.example.ecommerce.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> items;
    private BigDecimal total;
    private String status;
    private LocalDateTime createdAt;
}
