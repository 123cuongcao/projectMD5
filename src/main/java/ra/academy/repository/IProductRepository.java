package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.academy.model.entity.Products;

import java.util.Date;

public interface IProductRepository extends JpaRepository<Products,Long> {
    Page<Products> findByNameContaining(String name, Pageable pageable);
    Page<Products> findByNameContainingOrDescription(String name, Pageable pageable,String description);
    @Query("SELECT p FROM Products p ORDER BY p.created_at DESC ")
    Page<Products> findNewProduct( Pageable pageable);

}
