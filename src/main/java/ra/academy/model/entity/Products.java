package ra.academy.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String sku;
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Double unit_price;
    private int stock_quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    private Date created_at;

    private Date updated_at;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProductStatus status;
//    @OneToMany(mappedBy = "product")
//    private List<OrderDetails> orderDetails;

}
