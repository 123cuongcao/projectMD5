package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;
import ra.academy.repository.IOrderRepository;
import ra.academy.service.IOrderService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    @Override
    public List<Orders> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Orders> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    @Override
    public Orders findById(Long id) {
        return repository.findById(id).orElseThrow(()->new NoSuchElementException("không tồn tại dơn hàng này:"));
    }
}
