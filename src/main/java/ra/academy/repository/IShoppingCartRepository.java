package ra.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.ShoppingCart;
import ra.academy.model.entity.User;

import java.util.List;
@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart ,Long> {
     void deleteByUser(User user);
     List<ShoppingCart> findByUser(User user);

}
