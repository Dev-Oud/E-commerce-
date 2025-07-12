package ecommerce.example.ecommerce.service.cart;

import ecommerce.example.ecommerce.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);

    Cart getOrCreateUserCart(Long userId);
}
