package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.OrderDetailId;
import ra.academy.model.entity.OrderDetails;
import ra.academy.model.entity.Products;

import java.util.List;
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetails, OrderDetailId> {


}

