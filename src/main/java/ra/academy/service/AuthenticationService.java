package ra.academy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.academy.exception.LoginFailException;
import ra.academy.model.dto.request.SignInDto;
import ra.academy.model.dto.request.SignUpDto;
import ra.academy.model.dto.response.AuthenticationResponse;
import ra.academy.model.entity.Role;
import ra.academy.model.entity.Roles;
import ra.academy.model.entity.User;
import ra.academy.repository.IRoleRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.security.jwt.JwtProvider;
import ra.academy.security.principle.UserDetailImpl;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UploadService uploadService;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse signIn(SignInDto signInDto) throws LoginFailException {
        try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new LoginFailException("Tài khoản hoặc mật khẩu không đúng");
        }

        User user = userRepository.findByUsername(signInDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username notfound"));
        String accessToken = jwtProvider.generateAccessToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);

//        UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<String> roles = userDetail.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();

        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
//                .list(roles)
                .email(user.getEmail())
                .build();
    }

    public void register(SignUpDto signUpDto) {
        String avatar = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt2911666%2F&psig=AOvVaw1JmB33nknVL8O9iYFW-8Ed&ust=1703213864408000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJDYxZ7En4MDFQAAAAAdAAAAABAD";
        if (!signUpDto.getAvatar().isEmpty()) {
            avatar = uploadService.uploadFileToServer(signUpDto.getAvatar());
        }
        List<Role> roleSet = new ArrayList<>();
        if (signUpDto.getListRoles() == null || signUpDto.getListRoles().isEmpty()) {
            roleSet.add(iRoleRepository.findByRoleName(Roles.ROLE_USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
        } else {
            signUpDto.getListRoles().forEach(s -> {
                switch (s) {
                    case "admin":
                        roleSet.add(iRoleRepository.findByRoleName(Roles.ROLE_ADMIN).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "user":
                    default:
                        roleSet.add(iRoleRepository.findByRoleName(Roles.ROLE_USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                }
            });
        }
        User user = User.builder()
                .username(signUpDto.getUsername())
                .address(signUpDto.getAddress())
                .created_at(new Date(System.currentTimeMillis()))
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .avatar(avatar)
                .roles(roleSet)
                .phone(signUpDto.getPhone())
                .email(signUpDto.getEmail())
                .shoppingCarts(new ArrayList<>())
                .status(true)
                .updated_at(null)
                .wishList(new ArrayList<>())
                .orders(new ArrayList<>())
                .fullName(signUpDto.getFullname())
                .build();
        userRepository.save(user);
    }

}
