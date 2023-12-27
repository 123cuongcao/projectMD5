package ra.academy.controller;

import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.academy.model.dto.response.UserCatalogResponse;
import ra.academy.service.ICategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/categories")
public class UserCatalogController {
    private final ICategoryService service;
    @GetMapping
    public ResponseEntity<Page<UserCatalogResponse>> getCatalog(Pageable pageable){
       return new ResponseEntity<>(service.findCategoryByUser(pageable), HttpStatus.OK);
    }
}
