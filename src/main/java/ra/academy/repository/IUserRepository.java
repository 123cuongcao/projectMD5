package ra.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String Username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
    boolean existsByPassword(String password);

    Page<User> findByFullNameContaining(String name, Pageable pageable);


}
