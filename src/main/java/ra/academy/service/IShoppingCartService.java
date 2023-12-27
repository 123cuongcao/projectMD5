package ra.academy.service;

import ra.academy.exception.UnauthorizedException;
import ra.academy.model.dto.response.ShoppingCartResponse;
import ra.academy.model.entity.Orders;
import ra.academy.model.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface IShoppingCartService {
    List<ShoppingCartResponse> getAllShoppingCart();

    ShoppingCartResponse addShoppingCart(Map<String,Integer> map) throws UnauthorizedException;
    ShoppingCartResponse changeShoppingCartQuantity(Long id,Map<String,Integer> map);
    void deleteShoppingCart(long id);
    void deleteAllShoppingCart() throws UnauthorizedException;
    ShoppingCart findById(Long id);
    Orders addOrder(Map<String,Long> map) throws UnauthorizedException;


}
