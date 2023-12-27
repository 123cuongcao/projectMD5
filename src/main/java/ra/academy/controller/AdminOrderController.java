package ra.academy.controller;

import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.Status;
import ra.academy.service.IOrderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/admin/orders")
public class AdminOrderController {
    private final IOrderService service;

    @GetMapping
    public ResponseEntity<List<Orders>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<Orders> findById(@PathVariable Long idOrder) throws NoSuchElementException {
        return new ResponseEntity<>(service.findById(idOrder), HttpStatus.OK);
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<Orders>> findByStatus(@PathVariable String orderStatus) {
        Status status = Status.valueOf(orderStatus);
        return new ResponseEntity<>(service.findByStatus(status), HttpStatus.OK);
    }


}
