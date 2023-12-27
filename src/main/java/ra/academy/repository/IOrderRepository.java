package ra.academy.repository;

import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByStatus(Status status);
}
