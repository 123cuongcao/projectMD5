package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.academy.exception.CatalogExist;
import ra.academy.model.dto.request.CategoryRequest;
import ra.academy.model.dto.response.UserCatalogResponse;
import ra.academy.model.entity.Categories;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Page<Categories> findAll(Pageable pageable) ;
    Categories edit(CategoryRequest catalogRequest) throws CatalogExist;
    boolean existsByName(String name);
    Optional<Categories> findById(Long id);
    Categories save( CategoryRequest categoryRequest);
    void deleteCategory(Long categories);

    Page<UserCatalogResponse> findCategoryByUser(Pageable pageable);

}
