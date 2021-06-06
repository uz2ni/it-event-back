package com.itevent.iteventapi.modules.shop.dto;

import com.itevent.iteventapi.modules.shop.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductListResDto {
    
    private List<ProductResDto> products;

    public ProductListResDto(List<Product> list) {
        List<ProductResDto> products = list.stream()
                .map(product -> ProductResDto.of(product))
                .collect(Collectors.toList());

        this.products = products;
    }
}
