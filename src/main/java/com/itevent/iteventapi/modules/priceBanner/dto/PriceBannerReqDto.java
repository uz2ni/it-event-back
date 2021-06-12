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

    @NotNull(message = "shopID를 입력해주세요.")
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
