package com.itevent.iteventapi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/*
	최신 업데이트 상품 크롤링
 */
@Service
public class CrawlingService {

	private static String SITE_URL = "https://mr-s.co.kr/";

	@PostConstruct
	public void getNewProduct() throws IOException {
		System.out.println("크롤링 시작");
		Document doc = Jsoup.connect(SITE_URL).get();

		// 상품 목록 파싱
		Elements items = doc.select("ul.prdList.grid4").last().select("li[id^=anchorBoxId]");

		// 요소 파싱 (썸네일이미지주소, 상품명, 상품가격)


		for(Element item : items) {

			String prdId = item.attr("id");
			String imgUrl = item.select("div.thumbnail div.prdImg img").attr("src");
			String title = item.select("div.description strong.name a span").last().text();
			String price = item.select("div.description ul li").last().select("span").get(1).text();

			//System.out.println("[prd id : " + prdId + "]");
			System.out.println("[prd id : " + prdId + "] title:" + title + ", imgUrl:" + imgUrl + ", price:" + price);
		}


	//	System.out.println(list);
	}
}
