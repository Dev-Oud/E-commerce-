package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.exception.ResourceNotFoundException;
import ecommerce.example.ecommerce.model.Cart;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.response.ApiResponse;
import ecommerce.example.ecommerce.security.UserDetailsImpl;
import ecommerce.example.ecommerce.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final ICartService cartService;

    //  GET carts
    @GetMapping("/my-cart{cartId}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId, Authentication authentication) {
        try {
            Cart cart = cartService.getCart(cartId);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User currentUser = userDetails.getUser();

            if (!cart.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(FORBIDDEN).body(new ApiResponse("Access denied", null));
            }

            return ResponseEntity.ok(new ApiResponse("Success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    //  DELETE carts
    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId, Authentication authentication) {
        try {
            Cart cart = cartService.getCart(cartId);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User currentUser = userDetails.getUser();

            if (!cart.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(FORBIDDEN).body(new ApiResponse("Access denied", null));
            }

            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
