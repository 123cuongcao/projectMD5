package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Products;
import ra.academy.model.entity.User;
import ra.academy.model.entity.WishList;

import java.util.Optional;
@Repository
public interface IWishListRepository extends JpaRepository<WishList,Long> {
    Page<WishList> findByUserId(Long id , Pageable pageable);
    Optional<WishList> findByUserAndProduct(User user, Products products);

}
