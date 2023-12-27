package ra.academy.controller;

import com.google.api.Http;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.CatalogExist;
import ra.academy.model.dto.request.CategoryRequest;
import ra.academy.model.entity.Categories;
import ra.academy.service.ICategoryService;
import ra.academy.service.IUserService;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api.watcher.com/v1/admin/categories")
public class AdminCategoryController {
    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Categories> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.save(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Categories> editCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId) throws NoSuchElementException, CatalogExist {
        categoryRequest.setId(categoryId);
        return new ResponseEntity<>(categoryService.edit(categoryRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws NoSuchElementException {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Categories>> getAllCategory(Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Categories> getCategory(@PathVariable Long categoryId) throws NoSuchElementException {
        return new ResponseEntity<>(categoryService.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Không có sản phẩm này")), HttpStatus.BAD_REQUEST);
    }


}
