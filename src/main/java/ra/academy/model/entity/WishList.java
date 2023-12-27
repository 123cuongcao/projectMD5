package ra.academy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wish_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
}
