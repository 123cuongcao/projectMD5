package ra.academy.service;

import ra.academy.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAllRole();

    Role findById(Long id);
}
