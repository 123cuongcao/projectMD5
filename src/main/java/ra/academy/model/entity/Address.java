package ra.academy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User me;

    private String full_address;

    @Column(length = 15)
    private String phone;

    @Column(length = 50)
    private String receiver_name;

}
