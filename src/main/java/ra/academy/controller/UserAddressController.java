package ra.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.dto.request.AddressRequest;
import ra.academy.model.entity.Address;
import ra.academy.service.IAddressService;
import ra.academy.service.impl.AddressService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/user/account/address")
public class UserAddressController {
    private final IAddressService service;

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody AddressRequest address) {
        return new ResponseEntity<>(service.addAddress(address), HttpStatus.OK);
    }

    @DeleteMapping("{idAddress}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long idAddress) {
        service.deleteAddress(idAddress);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Address>> getUserAddress(Pageable pageable) {
        return new ResponseEntity<>(service.getAllUSerAddress(pageable), HttpStatus.OK);
    }

    @GetMapping("{idAddress}")
    public ResponseEntity<Address> getAddress(@PathVariable Long idAddress) {
        return new ResponseEntity<>(service.findAddressById(idAddress), HttpStatus.OK);
    }
}
