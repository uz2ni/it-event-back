package com.itevent.iteventapi.modules.priceBanner.dto;

import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.file.dto.FileDto;
import com.itevent.iteventapi.modules.priceBanner.PriceBanner;
import com.itevent.iteventapi.modules.shop.dto.ShopResDto;
import lombok.Data;

@Data
public class PriceBannerResDto {

    private Long id;

    private ShopResDto shop;

    private String title;

    private Long price;

    private FileDto image;

    private String landingUrl;

    public static PriceBannerResDto of(PriceBanner priceBanner) {
        PriceBannerResDto priceBannerResDto = ModelMapperUtils.getModelMapper().map(priceBanner, PriceBannerResDto.class);

        ShopResDto shopResDto = ModelMapperUtils.getModelMapper().map(priceBanner.getShop(), ShopResDto.class);
        priceBannerResDto.setShop(shopResDto);

        FileDto fileDto = ModelMapperUtils.getModelMapper().map(priceBanner.getImage(), FileDto.class);
        priceBannerResDto.setImage(fileDto);

        return priceBannerResDto;
    }
}