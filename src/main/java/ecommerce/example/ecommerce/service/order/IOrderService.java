package ecommerce.example.ecommerce.service.order;

import ecommerce.example.ecommerce.dto.OrderDto;
import ecommerce.example.ecommerce.model.Order;
import ecommerce.example.ecommerce.request.CheckoutRequest;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId, CheckoutRequest request);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
