package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ra.academy.model.dto.request.AddressRequest;
import ra.academy.model.entity.Address;
import ra.academy.model.entity.User;
import ra.academy.repository.IAddressRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.service.IAddressService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final IAddressRepository repository;
    private final IUserRepository userRepository;

//    còn thiếu bắt trùng lặp address;

    @Override
    public Address addAddress(AddressRequest addressRequest) {


        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));

        Address address = Address.builder()
                .full_address(addressRequest.getFull_address())
                .phone(addressRequest.getPhone())
                .receiver_name(addressRequest.getReceiver_name())
                .me(user)
                .province(addressRequest.getProvince())
                .city(addressRequest.getCity())
                .district(addressRequest.getDistrict())
                .isDefault(false)
                .build();
        return repository.save(address);

    }

    @Override
    public void deleteAddress(Long id) {
        repository.delete(findAddressById(id));
    }

    @Override
    public Page<Address> getAllUSerAddress(Pageable pageable) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại"));
        return repository.findAllByMe(user, pageable);
    }

    @Override
    public Address findAddressById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Người dùng không tồn tại địa chỉ này"));
    }
}
