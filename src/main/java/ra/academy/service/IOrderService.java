package ra.academy.service;

import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll();
    List<Orders> findByStatus(Status status);
    Orders findById(Long id);
}
