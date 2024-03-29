package com.itevent.iteventapi.crawler.brand;

import com.itevent.iteventapi.crawler.Crawler;
import com.itevent.iteventapi.modules.shop.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class mrStreetCrawler extends Crawler {

    public mrStreetCrawler(String siteUrl) {
        super(siteUrl);
    }

    @Override
    protected Elements getItems(Document doc) {
        return doc.select("ul.prdList.grid4").last().select("li[id^=anchorBoxId]");
    }

    @Override
    protected Product parseItem(Element item) {

        System.out.println(item.select("div.description ul li > strong.title").first().siblingElements());

        String price = "";
        if (item.select("div.description ul li").last().select("span > font").attr("color").equals("blue")) {
            price = item.select("div.description ul li").get(1).select("span").get(1).text().split("원")[0];
        } else {
            price = item.select("div.description ul li").last().select("span").get(1).text().split("원")[0];
        }

        Product product = Product.builder()
                .productId(item.attr("id").split("_")[1])
                .title(item.select("div.description strong.name a span").last().text())
                .price(price)
                .thumbUrl(item.select("div.thumbnail div.prdImg img").attr("src"))
                .detailUrl(item.select("div.thumbnail div.prdImg > a").attr("href"))
                .build();

        return product;
    }
}
