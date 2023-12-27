package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.academy.exception.*;
import ra.academy.model.dto.request.UpdateAccountRequest;
import ra.academy.model.entity.Categories;
import ra.academy.model.entity.Role;
import ra.academy.model.entity.User;
import ra.academy.repository.IRoleRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.service.IRoleService;
import ra.academy.service.IUserService;
import ra.academy.service.UploadService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final UploadService uploadService;
    private final PasswordEncoder encoder;

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsByUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Optional<User> findById(Long id) throws NoSuchElementException {

        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable, String name) {
        pageable = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        return userRepository.findByFullNameContaining(name, pageable);
    }


    @Override
    public void editUserRole(Map<String, Object> map, Long userId) throws Exception {
        try {
            User user = findById(userId).orElseThrow(() -> new NoSuchElementException("Không tồn tại người dùng"));

            Object roleIdObj = map.get("roleId");
            if (!(roleIdObj instanceof Number)) {
                throw new IllegalArgumentException("Kiểu dữ liệu không hợp lệ cho roleId");
            }
            long roleId = ((Number) roleIdObj).longValue();

            List<Role> roles = user.getRoles().stream()
                    .filter(a -> !a.getId().equals(roleId))
                    .collect(Collectors.toList());
            user.setRoles(roles);
            save(user);
        } catch (Exception e) {
            throw new Exception("Lỗi khi xử lý quyền người dùng", e);
        }
    }

    @Override
    public void setUserStatus(Long userId) {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng này"));
        user.setStatus(!user.isStatus());
        save(user);
    }

    @Override
    public void addRoleForUser(Long userId, Long roleId) throws NoSuchElementException, ExistRole {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng phù hợp"));
        if (user.getRoles().stream().anyMatch(a -> a.getId().equals(roleId))) {
            throw new ExistRole("Người dùng đã tồn tại quyền này");
        } else {
            user.getRoles().add(roleService.findById(roleId));
            save(user);
        }
    }

    @Override
    public User getAccount() throws UnauthorizedException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetail.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Không tồn tại người dùng"));
        if (user.getUsername().equals(username)) {
            return user;
        } else {
            throw new UnauthorizedException("Not authorized");
        }
    }

    @Override
    public User updateAccount(UpdateAccountRequest c) throws UnauthorizedException, EmailExistException, PhoneExistException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetail.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Không tồn tại người dùng"));
        if (existsByEmail(c.getEmail())) {
            throw new EmailExistException("Email đã tồn tại ");
        }
        if (existsByPhone(c.getPhone())) {
            throw new PhoneExistException("Số điện thoại đã tồn tãi");
        }
        if (user.getUsername().equals(username)) {
            user.setUpdated_at(new Date(System.currentTimeMillis()));
            user.setAvatar(uploadService.uploadFileToServer(c.getAvatar()));
            user.setAddress(c.getAddress());
            user.setFullName(c.getFullname());
            user.setEmail(c.getEmail());
            user.setPhone(c.getPhone());
        }
        return userRepository.save(user);
    }

    @Override
    public boolean existByPassword(String password) {
        return userRepository.existsByPassword(password);
    }

    @Override
    public void changePassword(Map<String, String> map) throws PasswordNotMatchException, PasswordExistException {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetail.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Không tồn tại người dùng"));
        String oldP = map.get("oldPassword");
        String newP = map.get("newPassword");
        String confirmP = map.get("confirmPassword");

        if (!encoder.matches(oldP, user.getPassword())) {
            throw new PasswordExistException("Sai mật khẩu ");
        }

        if (!newP.equals(confirmP)) {
            throw new PasswordNotMatchException("Mật khẩu nhập lại không trùng khớp");
        }
        user.setPassword(encoder.encode(newP));
        save(user);

    }
}
