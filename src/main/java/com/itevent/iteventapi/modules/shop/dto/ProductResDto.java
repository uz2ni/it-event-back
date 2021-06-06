package com.itevent.iteventapi.modules.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itevent.iteventapi.common.utils.DateFormatUtils;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.shop.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResDto {

    private Long id;

    private String productId;

    private String title;

    private String price;

    private String salePrice;

    private String thumbUrl;

    private String detailUrl;

    private Long shopId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DEFAULT_DATE_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;

    public static ProductResDto of(Product product) {
        return ModelMapperUtils.getModelMapper().map(product, ProductResDto.class);
    }

}
