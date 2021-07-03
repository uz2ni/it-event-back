package com.itevent.iteventapi.modules.priceBanner;

import com.itevent.iteventapi.modules.file.File;
import com.itevent.iteventapi.modules.file.FileService;
import com.itevent.iteventapi.modules.file.dto.FileDto;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerListResDto;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerReqDto;
import com.itevent.iteventapi.modules.priceBanner.dto.PriceBannerResDto;
import com.itevent.iteventapi.modules.shop.Shop;
import com.itevent.iteventapi.modules.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceBannerService {

    private final ShopService shopService;
    private final FileService fileService;
    private final PriceBannerRepository priceBannerRepository;


    public void createBanner(PriceBannerReqDto reqDto, MultipartFile mfile) throws IOException {

        // file upload
        Long fileId = fileService.upload(mfile);
        FileDto fileDto = fileService.getFile(fileId);
        File file = File.of(fileDto);

        Shop shop = shopService.getShopAndExistCheck(reqDto.getShopId());

        PriceBanner priceBanner = PriceBanner.of(reqDto, shop, file);
        priceBannerRepository.save(priceBanner);
    }

    public PriceBannerResDto getBanner(Long id) {
        PriceBanner priceBanner = getPriceBannerAndExistCheck(id);
        return PriceBannerResDto.of(priceBanner);
    }

    private PriceBanner getPriceBannerAndExistCheck(Long id) {
        PriceBanner priceBanner = priceBannerRepository.findById(id).orElse(null);
        if(priceBanner == null) {
            throw new IllegalArgumentException("요청한 배너가 존재하지 않습니다. [id : " + id + "]");
        }
        return priceBanner;
    }

    public PriceBannerListResDto getBannerAll() {
        List<PriceBanner> priceBanners = priceBannerRepository.findAll();

        return new PriceBannerListResDto((priceBanners));
    }

    public void updateBanner(Long id, PriceBannerReqDto priceBannerDto) {
        PriceBanner priceBanner = getPriceBannerAndExistCheck(id);
        PriceBanner.of(priceBannerDto, priceBanner);
    }

    public void deleteEvent(Long id) {
        getPriceBannerAndExistCheck(id);
        priceBannerRepository.deleteById(id);
    }
}
