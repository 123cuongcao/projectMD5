package ra.academy.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ra.academy.exception.ProductExist;
import ra.academy.model.dto.request.ProductRequest;
import ra.academy.model.dto.response.ProductResponse;
import ra.academy.model.entity.ProductStatus;
import ra.academy.model.entity.Products;
import ra.academy.repository.IOrderDetailRepository;
import ra.academy.repository.IOrderRepository;
import ra.academy.repository.IProductRepository;
import ra.academy.service.ICategoryService;
import ra.academy.service.IProductService;
import ra.academy.service.UploadService;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final UploadService service;
    private final ICategoryService categoryService;


    @Override
    public Page<Products> findAll(Pageable pageable, String name) {
        pageable = PageRequest.of(pageable.getPageNumber(), 5, pageable.getSort());
        return repository.findByNameContaining(name, pageable);
    }

    @Override
    public Products save(ProductRequest productRequest) throws NoSuchElementException {

        Products product = Products.builder()
                .name(productRequest.getName())
                .categories(categoryService.findById(productRequest.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Không tồn tại sanh mục")))
                .image(service.uploadFileToServer(productRequest.getImage()))
                .description(productRequest.getDescription())
                .unit_price(productRequest.getUnit_price())
                .stock_quantity(productRequest.getStock_quantity())
                .created_at(new Date(System.currentTimeMillis()))
                .updated_at(null)
                .status(ProductStatus.ACTIVE)
                .build();
        product.setSku(generateUUID().toString());
        return repository.save(product);
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        repository.delete(findById(id).orElseThrow(() -> new NoSuchElementException("Không tồn tại danh mục")));
    }

    @Override
    public Products edit(ProductRequest p) throws NoSuchElementException, ProductExist {
        Products products = findById(p.getId()).orElseThrow(() -> new NoSuchElementException("sản phẩm không tồn tại"));
        try {
            products.setName(p.getName() == null ? products.getName() : p.getName());
            products.setCategories(p.getCategoryId() == null ? products.getCategories() : categoryService.findById(p.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Không tồn tại danh mục")));
            products.setDescription(p.getDescription() == null ? products.getDescription() : p.getDescription());
            products.setImage(p.getImage() == null ? products.getImage() : service.uploadFileToServer(p.getImage()));
            products.setUnit_price(p.getUnit_price() == null ? products.getUnit_price() : p.getUnit_price());
            products.setStock_quantity(p.getStock_quantity() == 0 ? products.getStock_quantity() : p.getStock_quantity());
            products.setUpdated_at(new Date(System.currentTimeMillis()));
            products.setStatus(p.getStatus() == null? products.getStatus() : ProductStatus.valueOf(p.getStatus()));
        } catch (Exception e) {
            throw new ProductExist("Tên sản phẩm đã tồn tại");
        }
        return repository.save(products);
    }

    @Override
    public Optional<Products> findById(Long id) throws NoSuchElementException {
        return repository.findById(id);
    }

    @Override
    public Page<ProductResponse> findAllByUser(Pageable pageable, String name) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        List<ProductResponse> list = repository.findByNameContainingOrDescription(name, pageable, name).getContent().stream().map(a -> ProductResponse.builder()
                .productName(a.getName())
                .unitPrice(a.getUnit_price())
                .id(a.getId())
                .description(a.getDescription())
                .imageUrl(a.getImage())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, repository.findByNameContainingOrDescription(name, pageable, name).getTotalElements());
    }

    @Override
    public Page<ProductResponse> getNewProduct(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        List<ProductResponse> list = repository.findNewProduct(pageable).getContent().stream().map(a -> ProductResponse.builder()
                .productName(a.getName())
                .unitPrice(a.getUnit_price())
                .id(a.getId())
                .description(a.getDescription())
                .imageUrl(a.getImage())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, repository.findNewProduct(pageable).getTotalElements());
    }

    @Override
    public List<ProductResponse> getBestSeller() {
        return repository.findBestSellerProduct().stream().map(a ->
                ProductResponse.builder()
                        .imageUrl(a.getImage())
                        .productName(a.getName())
                        .unitPrice(a.getUnit_price())
                        .description(a.getDescription())
                        .id(a.getId())
                        .build()).collect(Collectors.toList());
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }


}

