package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.academy.exception.WishListExist;
import ra.academy.model.entity.WishList;

import java.util.Map;

public interface IWishListService {
    Page<WishList> getAllWishListByUser(Pageable pageable);

    void deleteWishList(Long id);

    WishList addWishList(Map<String,Long> map) throws WishListExist;

    WishList findById(Long id);


}
