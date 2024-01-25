package ra.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.dto.response.OrderResponse;
import ra.academy.model.entity.Status;
import ra.academy.service.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/api.watcher.com/v1/user/history1")
//@RequiredArgsConstructor
public class UserOrderController {
    private IOrderService service;

    public UserOrderController(IOrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllUserOrder() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getByStatus(@PathVariable Status orderStatus) {
        return new ResponseEntity<>(service.findByStatus(orderStatus), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(service.cancelOrder(Status.CANCEL, orderId), HttpStatus.OK);
    }
}
