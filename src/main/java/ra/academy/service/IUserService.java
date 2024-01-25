package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.academy.exception.*;
import ra.academy.model.dto.request.UpdateAccountRequest;
import ra.academy.model.dto.response.OrderResponse;
import ra.academy.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IUserService {
    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
    boolean existByPassword(String password);

    Optional<User> findById(Long id);

    Page<User> findAll(Pageable pageable,String name);

    void setUserStatus(Long userId);

    void editUserRole(Map<String ,Object> map ,Long userId) throws Exception;

    void addRoleForUser(Long userId,Long roleId) throws ExistRole;

    User getAccount() throws UnauthorizedException;

    User updateAccount(UpdateAccountRequest updateAccountRequest) throws UnauthorizedException, EmailExistException, PhoneExistException;

    void changePassword(Map<String,String> map) throws PasswordNotMatchException, PasswordExistException;



}
