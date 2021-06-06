package com.itevent.iteventapi.modules.shop;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.shop.dto.ProductListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shop/{shopNo}/products")
    public ResponseEntity<JsonResponse> getProductsByShop(@PathVariable Long shopNo) {
        ProductListResDto ProductListResDto = shopService.getProducts(shopNo);
        return new ResponseEntity<JsonResponse>(new JsonResponse(ProductListResDto), HttpStatus.OK);
    }

}
