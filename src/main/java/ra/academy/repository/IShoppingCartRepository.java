package ra.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.academy.model.entity.ShoppingCart;
import ra.academy.model.entity.User;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart ,Long> {
     void deleteByUser(User user);

}
