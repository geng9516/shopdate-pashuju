package shopdate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShopDate {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "https://tabelog.com/rstLst/washoku/";
		Document document = Jsoup.parse(new URL(url), 3000);
		Elements element = document.getElementsByClass("js-rstlist-info rstlist-info");

		Elements elements = element.tagName("list-rst__rst-name-target cpy-rst-name");

		for (Element el : elements) {
			String url2 = el.getElementsByTag("a").eq(0).attr("href");
			Document document2 = Jsoup.parse(new URL(url2), 3000);
			Elements element2 = document.getElementsByClass("rstinfo-table");
			String shopName = element2.tagName("td").eq(1).text();
			System.out.println(shopName);
		}
	}
}
