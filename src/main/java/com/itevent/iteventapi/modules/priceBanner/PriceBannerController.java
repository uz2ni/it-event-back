package com.itevent.iteventapi.modules.priceBanner;

import com.itevent.iteventapi.common.response.JsonResponse;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerReqDto;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PriceBannerController {

    private final PriceBannerService priceBannerService;

    @PostMapping("/price-banner")
    public ResponseEntity<JsonResponse> addPriceBanner(@Valid @RequestBody PriceBannerReqDto priceBannerDto) {
        priceBannerService.createBanner(priceBannerDto);
        return new ResponseEntity<JsonResponse>(new JsonResponse(), HttpStatus.OK);
    }

    @GetMapping("/price-banner/{id}")
    public ResponseEntity<JsonResponse> getPriceBanner(@PathVariable Long id) {
        PriceBannerResDto priceBannerResDto = priceBannerService.getBanner(id);
        return new ResponseEntity<JsonResponse>(new JsonResponse(priceBannerResDto), HttpStatus.OK);
    }

}