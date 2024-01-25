package ra.academy.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.*;
import ra.academy.model.dto.request.UpdateAccountRequest;
import ra.academy.model.entity.User;
import ra.academy.service.IUserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/user/account")
public class UserAccountController {
    private final IUserService service;

    @GetMapping
    public ResponseEntity<User> getUserAccount() throws UnauthorizedException {
        return new ResponseEntity<>(service.getAccount(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUserAccount(@ModelAttribute @Valid UpdateAccountRequest updateAccountRequest) throws UnauthorizedException, EmailExistException, PhoneExistException {
        return new ResponseEntity<>(service.updateAccount(updateAccountRequest), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> map) throws PasswordExistException, PasswordNotMatchException {
        service.changePassword(map);
        return new ResponseEntity<>("Đã thay đổi mật khẩu thành công", HttpStatus.OK);
    }

}
