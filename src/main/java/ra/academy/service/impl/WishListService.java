package ra.academy.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ra.academy.exception.WishListExist;
import ra.academy.model.entity.Products;
import ra.academy.model.entity.User;
import ra.academy.model.entity.WishList;
import ra.academy.repository.IProductRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.repository.IWishListRepository;
import ra.academy.security.principle.UserDetailImpl;
import ra.academy.service.IUserService;
import ra.academy.service.IWishListService;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListService implements IWishListService {
    private final IWishListRepository repository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;

    @Override
    public Page<WishList> getAllWishListByUser(Pageable pageable) throws NoSuchElementException {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(NoSuchElementException::new).getId();
        return repository.findByUserId(id, pageable);
    }


    @Override
    public void deleteWishList(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public WishList findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public WishList addWishList(Map<String, Long> map) throws NoSuchElementException, WishListExist {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));
        Products products = productRepository.findById(map.get("productId")).orElseThrow(() -> new NoSuchElementException("Không tồn tại sản phẩm này"));

        Optional<WishList> existingWishList = repository.findByUserAndProduct(user, products);

        if (existingWishList.isEmpty()) {
            WishList wishList = WishList.builder()
                    .user(user)
                    .product(products)
                    .build();
            return repository.save(wishList);
        } else {
            throw new WishListExist("Mục yêu thích này đã tồn tại ");
        }

    }
}
