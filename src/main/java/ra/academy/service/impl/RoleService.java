package ra.academy.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ra.academy.model.entity.Role;
import ra.academy.repository.IRoleRepository;
import ra.academy.service.IRoleService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository repository;
    @Override
    public List<Role> findAllRole() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
