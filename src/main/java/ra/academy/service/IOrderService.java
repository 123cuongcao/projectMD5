package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.academy.model.dto.response.OrderResponse;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    List<OrderResponse> findAll();

    List<OrderResponse> findByStatus(Status status);

    OrderResponse findById(Long id);

    OrderResponse cancelOrder(Status status,Long id);

    List<Orders> findAllByAdmin();

    List<Orders> findAllByStatusByAdmin(Status status);

    Orders findByIdByAdmin(Long id);

    Double incomeByTime(Date start , Date end);
    void editOrderStatus(Long id);



}
