package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dto.OrderDto;
import ecommerce.example.ecommerce.exception.ResourceNotFoundException;
import ecommerce.example.ecommerce.model.Order;
import ecommerce.example.ecommerce.request.CheckoutRequest;
import ecommerce.example.ecommerce.response.ApiResponse;
import ecommerce.example.ecommerce.security.UserDetailsImpl;
import ecommerce.example.ecommerce.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    //  POST orders checkout
    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(
            @RequestBody CheckoutRequest request,
            Authentication authentication
    ) {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long userId = userDetails.getUser().getId();

            Order order = orderService.placeOrder(userId, request);
            return ResponseEntity.ok(new ApiResponse("Order placed successfully!", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Checkout failed!", e.getMessage()));
        }
    }

    //  GET orders
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId, Authentication authentication) {
        try {
            OrderDto order = orderService.getOrder(orderId);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long currentUserId = userDetails.getUser().getId();

            if (!order.getUserId().equals(currentUserId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse("Access denied", null));
            }

            return ResponseEntity.ok(new ApiResponse("Order found!", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Order not found!", e.getMessage()));
        }
    }

    //  GET  user order history
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long currentUserId = userDetails.getUser().getId();

        if (!userId.equals(currentUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse("Access denied", null));
        }

        try {
            List<OrderDto> orders = orderService.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("User order history fetched!", orders));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("No orders found!", e.getMessage()));
        }
    }
}
