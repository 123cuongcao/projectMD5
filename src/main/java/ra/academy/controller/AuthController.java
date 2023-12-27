package ra.academy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.exception.LoginFailException;
import ra.academy.model.dto.request.SignInDto;
import ra.academy.model.dto.request.SignUpDto;
import ra.academy.model.dto.response.AuthenticationResponse;
import ra.academy.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn( @Valid @RequestBody SignInDto signInDto) throws LoginFailException {
        return new ResponseEntity<>(authenticationService.signIn(signInDto), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @ModelAttribute SignUpDto signInDto1) {
        authenticationService.register(signInDto1);
        return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
    }

}
