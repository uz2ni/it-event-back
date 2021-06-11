package com.itevent.iteventapi.modules.priceBanner.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceBannerReqDto {

    @NotNull(message = "브랜드가 존재하지 않습니다.")
    private Long shopId;

    @NotBlank
    private String title;

    @NotNull
    private Long price;

    @NotBlank
    private String bannerImage;

    @NotBlank
    private String landingUrl;
}
