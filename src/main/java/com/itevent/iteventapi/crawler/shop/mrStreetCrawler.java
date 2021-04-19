package com.itevent.iteventapi.crawler.shop;

import com.itevent.iteventapi.crawler.Crawler;
import com.itevent.iteventapi.crawler.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class mrStreetCrawler extends Crawler {

    public mrStreetCrawler(String siteUrl) {
        super(siteUrl);
    }

    @Override
    protected Elements getItems(Document doc) {
        return doc.select("ul.prdList.grid4").last().select("li[id^=anchorBoxId]");
    }

    @Override
    protected List<Product> parseData(Elements items) {
        List<Product> list = new ArrayList<>();
        for(Element item : items) {
            Product product = Product.builder()
                                    .productId(item.attr("id").split("_")[1])
                                    .title(item.select("div.description strong.name a span").last().text())
                                    .price(item.select("div.description ul li").last().select("span").get(1).text().split("원")[0])
                                    .thumbUrl(item.select("div.thumbnail div.prdImg img").attr("src"))
                                    .detailUrl(item.select("div.thumbnail div.prdImg > a").attr("href"))
                                    .build();
            list.add(product);
        }
        return list;
    }
}
