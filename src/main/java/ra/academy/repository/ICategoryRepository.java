package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.dto.response.UserCatalogResponse;
import ra.academy.model.entity.Categories;

import javax.xml.catalog.Catalog;
import java.util.Optional;
@Repository
public interface ICategoryRepository extends JpaRepository<Categories, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
   Optional <Categories> findByName(String name);

   boolean  existsByNameAndIdNot(String name,Long id);

   Page<Categories> findByStatus(boolean b, Pageable pageable);

}
