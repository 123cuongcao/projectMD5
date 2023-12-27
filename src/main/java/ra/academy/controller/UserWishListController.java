package ra.academy.controller;

import com.google.api.Http;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.WishListExist;
import ra.academy.model.dto.request.WishListRequest;
import ra.academy.model.entity.WishList;
import ra.academy.security.principle.UserDetailImpl;
import ra.academy.service.IWishListService;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api.watcher.com/v1/user/wish-list")
public class UserWishListController {
    private final IWishListService service;
    @GetMapping
    public ResponseEntity<Page<WishList>> getWishList(Pageable pageable)  {
        return  new ResponseEntity<>(service.getAllWishListByUser(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishList> addWishlist(@RequestBody Map<String, Long> map) throws NoSuchElementException, WishListExist {
        return new ResponseEntity<>(service.addWishList(map),HttpStatus.CREATED);
    }

    @DeleteMapping("/{wishListId}")
    public ResponseEntity<String> deleteWishList(@PathVariable Long wishListId) throws NoSuchElementException {
        service.deleteWishList(wishListId);
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }



}
