package ra.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.academy.model.dto.request.TimeRequest;
import ra.academy.model.dto.response.ProductResponse;
import ra.academy.service.IOrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.watcher.com/v1/admin/dash-board/sales")
public class AdminDashboardController {
    private final IOrderService service;
//    @GetMapping("/best-seller-product")
//    public ResponseEntity<ProductResponse> getBestSeller(){
//        return new ResponseEntity<>();
//    }
//
//    @GetMapping("/category")
//    public  ResponseEntity<ProductResponse> getCatalogIncome(){
//        return  new ResponseEntity<>();
//    }


    @GetMapping
    public ResponseEntity<Double> getInComeByTime(@RequestBody TimeRequest timeRequest){
        return new ResponseEntity<>(service.incomeByTime(timeRequest.getStart(),timeRequest.getEnd()), HttpStatus.OK);
    }


}
