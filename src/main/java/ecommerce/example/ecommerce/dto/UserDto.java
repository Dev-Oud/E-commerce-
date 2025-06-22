package ecommerce.example.ecommerce.dto;

//import ecommerce.example.ecommerce.model.Cart;
//import ecommerce.example.ecommerce.model.Order;
import lombok.Data;

//import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    //private List<CartItemDto> orders;
    private CartDto cart;
}

