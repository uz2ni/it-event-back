package com.itevent.iteventapi.modules.priceBanner;

import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerReqDto;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerResDto;
import com.itevent.iteventapi.modules.shop.Shop;
import com.itevent.iteventapi.modules.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceBannerService {

    private final ShopService shopService;
    private final PriceBannerRepository priceBannerRepository;

    public void createBanner(PriceBannerReqDto reqDto) {
        Shop shop = shopService.getShopAndExistCheck(reqDto.getShopId());
        PriceBanner priceBanner = PriceBanner.of(reqDto, shop);
        priceBannerRepository.save(priceBanner);
    }
}
