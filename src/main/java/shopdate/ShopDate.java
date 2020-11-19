package shopdate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShopDate {
	public static void main(String[] args)throws MalformedURLException, IOException {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= 100 / 20; i++) {
			String url2 = "https://tabelog.com/rstLst/washoku/" + i + "/";
			Document document4 = Jsoup.parse(new URL(url2), 30000);
			Elements elements = document4.getElementsByClass("list-rst__rst-name-target cpy-rst-name");
			for (Element el : elements) {
				String url3 = el.getElementsByClass("list-rst__rst-name-target cpy-rst-name").eq(0).attr("href");
				Document document2 = Jsoup.parse(new URL(url3), 30000);
				Element element2 = document2.getElementById("contents-rstdata");
				Elements elements2 = element2.getElementsByClass("c-table c-table--form rstinfo-table__table").eq(0);
				for (Element e : elements2) {
					String name = e.getElementsByTag("td").eq(0).text();
					String tel = e.getElementsByTag("td").eq(2).text();
					String add = e.getElementsByTag("td").eq(4).eq(0).toggleClass("listlink").text();
					// System.out.println(name + "," + tel + "," +add.substring(0,add.lastIndexOf("大きな地図を見る 周辺のお店を探す")));
					list.add(name + "," + tel + "," + add.substring(0,add.lastIndexOf("大きな地図を見る 周辺のお店を探す")));
				}
			}
		}

		Jdbc jdbc = new Jdbc();
		try {
			jdbc.getDb();

			for (String s : list) {
				String[] s2 = s.split(",");
				Shop sp = new Shop();
				sp.setName(s2[0]);
				sp.setTel(s2[1]);
				sp.setAddress(s2[2]);

				jdbc.insertShopDate(sp);
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (jdbc != null) {
				try {
					jdbc.closeDb();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
