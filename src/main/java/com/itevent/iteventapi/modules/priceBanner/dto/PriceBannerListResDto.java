package com.itevent.iteventapi.modules.priceBanner.dto;

import com.itevent.iteventapi.modules.priceBanner.PriceBanner;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PriceBannerListResDto {

    private List<PriceBannerResDto> priceBanners;

    public PriceBannerListResDto(List<PriceBanner> list) {
        List<PriceBannerResDto> priceBanners = list.stream()
                .map(banner -> PriceBannerResDto.of(banner))
                .collect(Collectors.toList());

        this.priceBanners = priceBanners;
    }
}