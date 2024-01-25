package ra.academy.controller;

import com.google.api.Http;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.ProductExist;
import ra.academy.model.dto.request.ProductRequest;
import ra.academy.model.entity.Categories;
import ra.academy.model.entity.Products;
import ra.academy.model.entity.User;
import ra.academy.service.IProductService;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api.watcher.com/v1/admin/products")
public class AdminProductController {
    private final IProductService service;

    @PostMapping
    public ResponseEntity<Products> addProduct(@ModelAttribute ProductRequest productRequest) {
        return new ResponseEntity<>(service.save(productRequest), HttpStatus.OK);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<Products> editProduct(@PathVariable Long idProduct, @ModelAttribute ProductRequest productRequest) throws NoSuchElementException, ProductExist {
        productRequest.setId(idProduct);
        return new ResponseEntity<>(service.edit(productRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long idProduct) throws NoSuchElementException {
        service.delete(idProduct);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Products> findProductById(@PathVariable Long idProduct) throws NoSuchElementException {
        return new ResponseEntity<>(service.findById(idProduct).orElseThrow(() -> new NoSuchElementException("không tìm thấy sản phẩm")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Products>> findAll(Pageable pageable,
                                                  @RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(service.findAll(pageable, name), HttpStatus.OK);
    }




}
