package ra.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.academy.model.dto.response.ProductResponse;
import ra.academy.service.IProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class UserProductController {
    private final IProductService service;


    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> getProductByUser(@RequestParam(defaultValue = "") String searchName, Pageable pageable) {
        return new ResponseEntity<>(service.findAllByUser(pageable, searchName), HttpStatus.OK);
    }

    @GetMapping("/new-product")
    public ResponseEntity<Page<ProductResponse>> getNewProduct(Pageable pageable) {
        return new ResponseEntity<>(service.getNewProduct(pageable), HttpStatus.OK);
    }

    @GetMapping("/best-seller-products")
    public ResponseEntity<List<ProductResponse>> getBestSellerProduct( ) {
        return new ResponseEntity<>(service.getBestSeller(), HttpStatus.OK);
    }


}
