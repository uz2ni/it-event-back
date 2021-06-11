package com.itevent.iteventapi.modules.shop;

import com.itevent.iteventapi.modules.event.Event;
import com.itevent.iteventapi.modules.shop.dto.ProductListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public ProductListResDto getProducts(Long shopNo) {
        Shop shop = getShopAndExistCheck(shopNo);
        List<Product> products = shop.getProducts();
        return new ProductListResDto(products);
    }

    public Shop getShopAndExistCheck(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if(shop == null) {
            throw new IllegalArgumentException("요청한 브랜드가 존재하지 않습니다. [id : " + id + "]");
        }
        return shop;
    }
}
