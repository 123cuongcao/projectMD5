package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Products;

import java.util.Date;
import java.util.List;
@Repository
public interface IProductRepository extends JpaRepository<Products,Long> {
    Page<Products> findByNameContaining(String name, Pageable pageable);
    Page<Products> findByNameContainingOrDescription(String name, Pageable pageable,String description);
    @Query("SELECT p FROM Products p ORDER BY p.created_at DESC ")
    Page<Products> findNewProduct( Pageable pageable);
    //    @Query(value = "select * from products where id=(select product_id from order_details group by product_id order by sum(order_quantity) desc  limit 10 )",nativeQuery = true)
    @Query(value = "SELECT * FROM Products p " +
            "WHERE p.id IN (SELECT od.product_id FROM Order_Details od join Orders o on o.id = od.order_id " +
            "where o.status = 'SUCCESS' GROUP BY od.product_id  " +
            "ORDER BY SUM(od.order_quantity) DESC ) LIMIT 1" , nativeQuery = true)
    List<Products> findBestSellerProduct();
}
