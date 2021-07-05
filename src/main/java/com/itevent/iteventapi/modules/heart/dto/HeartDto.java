package com.itevent.iteventapi.modules.heart.dto;

import com.itevent.iteventapi.modules.heart.HeartType;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeartDto {

    @NotNull(message = "targetID를 지정해주세요.")
    private Long targetId;

    @NotNull(message = "올바른 HEART 타입 값을 지정하세요.(SHOP / PRODUCT)")
    private HeartType heartType;

}
