package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ra.academy.exception.CatalogExist;
import ra.academy.model.dto.request.CategoryRequest;
import ra.academy.model.dto.response.UserCatalogResponse;
import ra.academy.model.entity.Categories;
import ra.academy.repository.ICategoryRepository;
import ra.academy.service.ICategoryService;

import javax.swing.text.html.Option;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService implements ICategoryService {
    private final ICategoryRepository repository;

    @Override
    public Page<Categories> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        return repository.findAll(pageable);
    }

    @Override
    public Categories save(CategoryRequest catalogRequest) {
        return repository.save(Categories.builder()
                .name(catalogRequest.getName())
                .id(catalogRequest.getId())
                .description(catalogRequest.getDescription())
                .products(catalogRequest.getProducts())
                .status(true)
                .build());
    }

    @Override
    public void deleteCategory(Long categories) throws NoSuchElementException {
        repository.delete(findById(categories).orElseThrow(() -> new NoSuchElementException("Không tồn tại danh mục này")));
    }

    @Override
    public Page<UserCatalogResponse> findCategoryByUser(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        List<UserCatalogResponse> list = repository.findByStatus(true, pageable).getContent().stream().map(a -> UserCatalogResponse.builder()
                .categoryName(a.getName())
                .id(a.getId())
                .description(a.getDescription())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, repository.findByStatus(true, pageable).getTotalElements());
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Optional<Categories> findById(Long id) throws NoSuchElementException {
        return repository.findById(id);
    }

    @Override
    public Categories edit(CategoryRequest catalogRequest) throws NoSuchElementException, CatalogExist {
        Categories categories;
        try {
            categories = findById(catalogRequest.getId()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục với ID: "));
            categories.setDescription(catalogRequest.getDescription());
            categories.setName(catalogRequest.getName());
            categories.setId(catalogRequest.getId());
            categories.setProducts(catalogRequest.getProducts());

        } catch (Exception e) {
            throw new CatalogExist("tên danh mục đã tồn tại");
        }
        return repository.save(categories);
    }

}
