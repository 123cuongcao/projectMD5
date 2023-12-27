package ra.academy.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.Order;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {

    @Column(length = 100)
    private String name;
    private Double unit_price;
    private int order_quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
}
