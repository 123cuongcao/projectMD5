package ra.academy.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.Order;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetails {

    @EmbeddedId
    private OrderDetailId id;

    @Column(length = 100)
    private String name;
    private Double unit_price;
    private int order_quantity;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Products product;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders orders;
}

//select * from product where id=(select product_id from orderDetail group by product_id order by sum(quantity) desc  limit 10   )