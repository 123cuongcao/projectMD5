package ra.academy.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.academy.exception.UnauthorizedException;
import ra.academy.model.dto.response.ShoppingCartResponse;
import ra.academy.model.entity.*;
import ra.academy.repository.*;
import ra.academy.service.IShoppingCartService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartService implements IShoppingCartService {
    private final IShoppingCartRepository repository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;
    private final IAddressRepository addressRepository;

    @Override
    public List<ShoppingCartResponse> getAllShoppingCart() {

        return repository.findAll().stream().map(a -> ShoppingCartResponse.builder()
                .id(a.getId())
                .productName(a.getProduct().getName())
                .unitPrice(a.getProduct().getUnit_price())
                .quantity(a.getOrder_quantity())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ShoppingCartResponse addShoppingCart(Map<String, Integer> map) throws UnauthorizedException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));

        Products products = productRepository.findById(Long.valueOf(map.get("idProduct"))).orElseThrow();
        Integer quantity = map.get("quantity");
        ShoppingCart shoppingCart = new ShoppingCart(null, user, products, quantity);
        repository.save(shoppingCart);
        return ShoppingCartResponse.builder()
                .id(shoppingCart.getId())
                .productName(shoppingCart.getProduct().getName())
                .unitPrice(shoppingCart.getProduct().getUnit_price())
                .quantity(shoppingCart.getOrder_quantity())
                .build();
    }

    @Override
    public ShoppingCartResponse changeShoppingCartQuantity(Long id, Map<String, Integer> map) {
        ShoppingCart shoppingCart = findById(id);
        shoppingCart.setOrder_quantity(map.get("quantity"));
        repository.save(shoppingCart);

        return ShoppingCartResponse.builder()
                .id(shoppingCart.getId())
                .productName(shoppingCart.getProduct().getName())
                .unitPrice(shoppingCart.getProduct().getUnit_price())
                .quantity(shoppingCart.getOrder_quantity())
                .build();
    }

    @Override
    public void deleteShoppingCart(long id) {
        repository.delete(findById(id));
    }

    @Override
    @Transactional
    public void deleteAllShoppingCart() throws UnauthorizedException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));
        repository.deleteByUser(user);

    }

    @Override
    public ShoppingCart findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Đơn hàng không tồn tại"));
    }

    @Transactional
    @Override
    public Orders addOrder(Map<String, Long> map) throws UnauthorizedException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));
        double total = user.getShoppingCarts()
                .stream()
                .map(cart ->
                        BigDecimal.valueOf(cart.getProduct().getUnit_price()).multiply(BigDecimal.valueOf(cart.getOrder_quantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
        Address address = addressRepository.findById(map.get("idAddress")).orElseThrow(() -> new NoSuchElementException("Không tồn tại địa chỉ này"));
        Orders o = new Orders();
        o.setUser(user);
        o.setNote("");
        o.setSerial_number(UUID.randomUUID().toString());
        o.setCreated_at(new Date(System.currentTimeMillis()));
        o.setStatus(Status.WAITING);
        o.setReceive_address(address.getFull_address());
        o.setReceive_name(address.getReceiver_name());
        o.setReceive_phone(address.getPhone());
        o.setTotal_price(total);
        o.setReceived_at(new Date(System.currentTimeMillis() + 86400000));
        deleteAllShoppingCart();
        return orderRepository.save(o);
    }


}
