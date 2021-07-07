package com.itevent.iteventapi.modules.heart.validate;

import com.itevent.iteventapi.modules.heart.dto.HeartDto;
import com.itevent.iteventapi.modules.shop.ProductRepository;
import com.itevent.iteventapi.modules.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class HeartValidator implements Validator {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return HeartDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HeartDto heartDto = (HeartDto)target;

        if(!isvalidId(heartDto)) {
            errors.rejectValue("targetId", "wrong.targetId", "유효하지 않은 targetID 입니다.");
        }
    }

    private boolean isvalidId(HeartDto dto) {
        if(dto.getHeartType().name().equals("PRODUCT") && productRepository.existsById(dto.getTargetId())){}
        else if(dto.getHeartType().name().equals("SHOP") && shopRepository.existsById(dto.getTargetId())){}
        else { return false; }
        return true;
    }
}
