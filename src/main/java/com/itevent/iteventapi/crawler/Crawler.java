package com.itevent.iteventapi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
	최신 업데이트 상품 크롤링
 */
abstract public class Crawler {

	private String siteUrl;

	public Crawler(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public List<Product> run() throws IOException {
		Document doc = Jsoup.connect(siteUrl).get();
		Elements items = getItems(doc);
		return parseData(items);

	}

	protected abstract Elements getItems(Document doc);

	private List<Product> parseData(Elements items) {
		List<Product> list = new ArrayList<>();
		for(Element item : items) {
			list.add(parseItem(item));
		}
		return list;
	}

	protected abstract Product parseItem(Element item);
}
