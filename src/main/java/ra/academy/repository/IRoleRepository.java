package ra.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Role;
import ra.academy.model.entity.Roles;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(Roles roles);



}
