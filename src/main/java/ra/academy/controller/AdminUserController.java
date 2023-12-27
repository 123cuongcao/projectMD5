package ra.academy.controller;

import com.google.api.Http;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.ExistRole;
import ra.academy.model.entity.User;
import ra.academy.service.IUserService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api.watcher.com/v1/admin/users")
public class AdminUserController {
    private final IUserService service;

    @GetMapping("/{idUser}")
    public ResponseEntity<User> searchUser(@PathVariable Long idUser) throws NoSuchElementException {
        return new ResponseEntity<>(service.findById(idUser).orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable,
                                              @RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(service.findAll(pageable, name), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> setUserStatus(@PathVariable Long userId) {
        service.setUserStatus(userId);
        return new ResponseEntity<>("Thay đổi trạng thái thành công", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/role")
    public ResponseEntity<String> editUserRole(@PathVariable Long userId, @RequestBody Map<String, Object> map) throws Exception {
        service.editUserRole(map, userId);
        return new ResponseEntity<>("Đã thay đổi quyền người dùng", HttpStatus.OK);
    }

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> addRoleForUser(@PathVariable Long userId, @PathVariable Long roleId) throws ExistRole {
        service.addRoleForUser(userId, roleId);
        return new ResponseEntity<>("Thêm quyền người dùng thành công ", HttpStatus.OK);
    }
    
    

}
