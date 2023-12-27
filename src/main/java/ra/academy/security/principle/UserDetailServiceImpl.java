package ra.academy.security.principle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.academy.model.entity.User;
import ra.academy.repository.IUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tên đăng nhập"));
        List<GrantedAuthority> list = user.getRoles().stream().map(r ->
                new SimpleGrantedAuthority(r.getRoleName().name())).collect(Collectors.toList());

        return UserDetailImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .grantedAuthorities(list)
                .build();
    }

}
