package ra.academy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ra.academy.validator.CategoryUnique;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,unique = true)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private boolean status;
    @OneToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Products> products;

}
