package com.itevent.iteventapi.modules.priceBanner;

import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerReqDto;
import com.itevent.iteventapi.modules.shop.Shop;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class PriceBanner extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Shop shop;

    private String title;

    private Long price;

    @Lob
    private String bannerImage;

    private String landingUrl;

    public static PriceBanner of(PriceBannerReqDto reqDto, Shop shop) {
        PriceBanner priceBanner = ModelMapperUtils.getModelMapper().map(reqDto, PriceBanner.class);
        priceBanner.setShop(shop);
        return priceBanner;
    }
}
