package com.itevent.iteventapi.crawler.brand;

import com.itevent.iteventapi.crawler.Crawler;
import com.itevent.iteventapi.crawler.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PapaRecipeCrawler extends Crawler {

    public PapaRecipeCrawler(String siteUrl) {
        super(siteUrl);
    }

    @Override
    protected Elements getItems(Document doc) {
        return doc.select("ul.prdList > li");
    }

    @Override
    protected Product parseItem(Element item) {

        // 제품 ID
        String productId = item.attr("id").split("_")[1];

        // 제품명
        String title = item.select(".prdInfo .prd_name > a > span").text();

        // 판매 가격 li>.prdInfo[ec-data-price]
        String price = item.select(".prdInfo").attr("ec-data-price");

        // 할인 가격 li>.prdInfo ul>li(2)>span
        String salePrice = "";
        Elements priceElements = item.select(".prdInfo ul > li");
        if (priceElements.size() == 2) {
            salePrice = priceElements.get(1).select("span").text();
            salePrice = salePrice.split(" ")[1];
            salePrice = salePrice.substring(0, salePrice.length()-1).replaceAll("\\,", "");
        }

        // 썸네일 이미지 주소
        String thumbUrl = item.select(".prdImg > .prd_tmb > a").attr("style");
        int start = thumbUrl.indexOf("('");
        thumbUrl = thumbUrl.substring(start + 2, thumbUrl.length() - 3);

        // 상세 페이지 주소 li>.prdInfo .prd_name>a[href]
        String detailUrl = item.select(".prdInfo .prd_name > a").attr("href");

        Product product = Product.builder()
                .productId(productId)
                .title(title)
                .price(price)
                .salePrice(salePrice)
                .thumbUrl(thumbUrl)
                .detailUrl(detailUrl)
                .build();

        return product;
    }
}
