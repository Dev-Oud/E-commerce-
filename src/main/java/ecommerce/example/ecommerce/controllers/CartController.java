package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.exception.ResourceNotFoundException;
import ecommerce.example.ecommerce.model.Cart;
import ecommerce.example.ecommerce.response.ApiResponse;
import ecommerce.example.ecommerce.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final ICartService cartService;

    //  Get carts 
    @GetMapping("/my-cart/{cartId}")
    @PreAuthorize("#cartId == principal.user.id")  
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    //  Delete carts 
    @DeleteMapping("/{cartId}")
    @PreAuthorize("#cartId == principal.user.id")  
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
