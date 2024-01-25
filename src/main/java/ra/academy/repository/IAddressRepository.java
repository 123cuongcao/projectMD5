package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Address;
import ra.academy.model.entity.User;
@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {
    Page<Address> findAllByMe(User me, Pageable pageable);
}
