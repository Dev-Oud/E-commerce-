package ecommerce.example.ecommerce.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutRequest {
    private String shippingAddress;
    private String shippingCity;
    private String shippingCountry;
    private String recipientName;
    private String recipientPhone;
}
