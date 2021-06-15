package com.itevent.iteventapi.modules.priceBanner;

import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerReqDto;
import com.itevent.iteventapi.modules.shop.Shop;
import lombok.*;
import org.modelmapper.PropertyMap;

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

    private String landingUrl;

    /* banner image */
    private String originalFileName;

    private String storedFilePath;

    private long fileSize;

    public static PriceBanner of(PriceBannerReqDto reqDto, Shop shop) {
        PriceBanner priceBanner = ModelMapperUtils.getModelMapper().map(reqDto, PriceBanner.class);
        priceBanner.setShop(shop);
        return priceBanner;
    }

    public static void of(PriceBannerReqDto reqDto, PriceBanner priceBanner) {
        ModelMapperUtils.getModelMapper().addMappings(new PropertyMap<PriceBannerReqDto, PriceBanner>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        }).map(reqDto, priceBanner);
    }

}
