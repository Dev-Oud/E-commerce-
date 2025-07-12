package ecommerce.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private BigDecimal price; // Total price for this line item (unit price * quantity)

    @Column(nullable = false)
    private String productName; // Snapshot of product name at time of purchase

    @Column(nullable = false)
    private BigDecimal productPriceAtPurchase; // Snapshot of unit price at purchase

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderItem(
            Order order,
            Product product,
            int quantity,
            BigDecimal price,
            String productName,
            BigDecimal productPriceAtPurchase
    ) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
        this.productPriceAtPurchase = productPriceAtPurchase;
    }
}
