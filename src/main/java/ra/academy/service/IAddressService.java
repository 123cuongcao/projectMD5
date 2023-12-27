package ra.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.academy.model.dto.request.AddressRequest;
import ra.academy.model.entity.Address;

public interface IAddressService {
    Address addAddress(AddressRequest addressRequest);
    void deleteAddress(Long id);
    Page<Address> getAllUSerAddress(Pageable pageable);
    Address findAddressById(Long id);


}
