package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ra.academy.model.dto.response.OrderResponse;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;
import ra.academy.model.entity.User;
import ra.academy.repository.IOrderRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.service.IOrderService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    private final IUserRepository userRepository;

    @Override
    public List<OrderResponse> findAll() {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));

        return repository.findByUser(user).stream().map(a -> OrderResponse.builder()
                .note(a.getNote())
                .serialNumber(a.getSerial_number())
                .totalPrice(a.getTotal_price())
                .createdAt(a.getCreated_at())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> findByStatus(Status status) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));
        return repository.findByStatusAndUser(status, user).stream().map(a -> OrderResponse.builder()
                .note(a.getNote())
                .serialNumber(a.getSerial_number())
                .totalPrice(a.getTotal_price())
                .createdAt(a.getCreated_at())
                .status(a.getStatus())
                .build()).collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Long id) {
        Orders a = repository.findById(id).orElseThrow(() -> new NoSuchElementException("không tồn tại dơn hàng này:"));
        return OrderResponse.builder()
                .note(a.getNote())
                .serialNumber(a.getSerial_number())
                .totalPrice(a.getTotal_price())
                .createdAt(a.getCreated_at())
                .build();
    }

    @Override
    public OrderResponse cancelOrder(Status status, Long id) throws NoSuchElementException {
        Orders orders = repository.findById(id).orElseThrow(() -> new NoSuchElementException("không tồn tại dơn hàng này"));
        orders.setStatus(status);
        repository.save(orders);
        return findById(id);
    }

    @Override
    public List<Orders> findAllByAdmin() {
        return repository.findAll();
    }

    @Override
    public List<Orders> findAllByStatusByAdmin(Status status) {
        return repository.findByStatus(status);
    }

    @Override
    public Orders findByIdByAdmin(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tồn tại đơn hàng này"));
    }

    @Override
    public Double incomeByTime(Date start, Date end) {
        return repository.findByTime(start, end);
    }

    @Override
    public void editOrderStatus(Long id) {
        Orders orders = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Không tồn tại đơn hàng này"));
        orders.setStatus(Status.SUCCESS);
        repository.save(orders);
    }


}
