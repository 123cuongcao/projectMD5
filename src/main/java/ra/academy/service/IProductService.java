package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.academy.exception.ProductExist;
import ra.academy.model.dto.request.ProductRequest;
import ra.academy.model.dto.response.ProductResponse;
import ra.academy.model.entity.Products;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Products> findAll(Pageable pageable , String name);
    Products save(ProductRequest productRequest);
    void delete(Long id);
    Products edit(ProductRequest productRequest) throws ProductExist;
    Optional<Products> findById(Long id);
//    Page<Products> findAllByNameContaining(Pageable pageable,String name);
    Page<ProductResponse> findAllByUser(Pageable pageable , String name);
    Page<ProductResponse> getNewProduct(Pageable pageable);
    List<ProductResponse> getBestSeller();
}
