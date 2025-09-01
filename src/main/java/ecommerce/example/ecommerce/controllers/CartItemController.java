package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.exception.ResourceNotFoundException;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.response.ApiResponse;
import ecommerce.example.ecommerce.security.UserDetailsImpl;
import ecommerce.example.ecommerce.service.cart.ICartItemService;
import ecommerce.example.ecommerce.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cartItems")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;

    // POST cartItems
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()") // only logged-in users can add items
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            User user = userDetails.getUser();
            cartItemService.addItemToCart(
                    cartService.getOrCreateUserCart(user.getId()).getId(),
                    productId,
                    quantity
            );
            return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    // DELETE cartItems
    @DeleteMapping("/{itemId}")
    @PreAuthorize("isAuthenticated()") // only logged-in users can remove items
    public ResponseEntity<ApiResponse> removeItemFromCart(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            User user = userDetails.getUser();
            cartItemService.removeItemFromCart(
                    cartService.getOrCreateUserCart(user.getId()).getId(),
                    itemId
            );
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    // PUT cartItems
    @PutMapping("/{itemId}")
    @PreAuthorize("isAuthenticated()") // only logged-in users can update items
    public ResponseEntity<ApiResponse> updateItemQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            User user = userDetails.getUser();
            cartItemService.updateItemQuantity(
                    cartService.getOrCreateUserCart(user.getId()).getId(),
                    itemId,
                    quantity
            );
            return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
