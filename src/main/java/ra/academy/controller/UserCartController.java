package ra.academy.controller;

import com.google.api.Http;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.UnauthorizedException;
import ra.academy.model.dto.response.ShoppingCartResponse;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.ShoppingCart;
import ra.academy.service.IShoppingCartService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/user/shopping-cart")
public class UserCartController {
    private final IShoppingCartService service;

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponse>> getAllShoppingCart() {
        return new ResponseEntity<>(service.getAllShoppingCart(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> addShoppingCart(@RequestBody Map<String, Integer> map) throws UnauthorizedException {
        return new ResponseEntity<>(service.addShoppingCart(map), HttpStatus.OK);
    }

    @PutMapping("/{idShoppingCart}")
    public ResponseEntity<ShoppingCartResponse> changeShoppingCartQuantity(@PathVariable Long idShoppingCart, @RequestBody Map<String, Integer> map) {
        return new ResponseEntity<>(service.changeShoppingCartQuantity(idShoppingCart, map), HttpStatus.OK);
    }

    @DeleteMapping("{idShoppingCart}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long idShoppingCart) {
        service.deleteShoppingCart(idShoppingCart);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllShoppingCart() throws UnauthorizedException {
        service.deleteAllShoppingCart();
        return new ResponseEntity<>("Xóa giỏ hàng thành công", HttpStatus.OK);
    }
    @PostMapping("/checkout")
    public ResponseEntity<Orders> checkOut(@RequestBody Map<String,Long> map) throws UnauthorizedException {
        return new ResponseEntity<>(service.addOrder(map),HttpStatus.OK);
    }
}
