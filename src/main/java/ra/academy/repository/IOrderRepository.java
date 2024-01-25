package ra.academy.repository;

import lombok.Data;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;
import ra.academy.model.entity.User;

import java.util.Date;
import java.util.List;
@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByStatus(Status status);

    List<Orders> findAll();

    Page<Orders> findByStatus(Pageable pageable, Status status);

    List<Orders> findByUser(User user);

    List<Orders> findByStatusAndUser(Status status, User user);

    @Query(value = "select SUM(o.total_Price) from orders o WHERE o.status = 'SUCCESS' AND (o.created_at BETWEEN ?1 AND  ?2) ", nativeQuery = true)
    Double findByTime(Date start, Date end);
}
