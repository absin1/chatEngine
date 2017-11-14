package absin.asasasas;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Google {

	public ApiAIResponse search(String q) throws IOException {
		ApiAIResponse aiResponse = new ApiAIResponse();
		String url = "https://www.google.co.in/search?q=" + q;
		Document document = Jsoup.connect(url).get();
		Elements elementsByClass = document.getElementsByClass("_Tgc");
		String response = "";
		for (Element element : elementsByClass) {
			response += element.text();
		}
		if (response.length() < 2) {
			Elements rightHand = document.getElementsByClass("xpdopen");
			for (Element element : rightHand) {
				Elements elementsByClass2 = element.getElementsByClass("kno-rdesc");
				for (Element element2 : elementsByClass2) {
					response += element2.text();
				}
			}
		}
		// System.err.println(response);
		try{
			response = response.replaceAll("\\(" + ".*" + "\\)", "");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("String shortening failed!");
		}
		JsonArray contextOut = new JsonArray();
		aiResponse.setContextOut(contextOut);
		aiResponse.setDisplayText(response);
		aiResponse.setSpeech(response);
		aiResponse.setSource("Google");
		return aiResponse;
	}

	public ApiAIResponse prepareGoogleNewsResponse() throws MalformedURLException, ProtocolException, IOException {
		ApiAIResponse aiResponse = new ApiAIResponse();
		String url = "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=fd3b8cc9043b46da91f47f5581b63581";
		String responze = "{\"status\":\"ok\",\"source\":\"google-news\",\"sortBy\":\"top\",\"articles\":[{\"author\":null,\"title\":\"This RSS feed URL is deprecated\",\"description\":\"Comprehensive up-to-date news coverage, aggregated from sources all over the world by Google News.\",\"url\":\"https://news.google.com/headlines\",\"urlToImage\":null,\"publishedAt\":\"2017-11-03T13:15:08Z\"},{\"author\":\"https://www.facebook.com/tory.newmyer\",\"title\":\"Analysis | The Finance 202: Wall Street escapes GOP tax cutters unscathed, so far\",\"description\":\"But stay tuned.\",\"url\":\"https://www.washingtonpost.com/news/powerpost/paloma/the-finance-202/2017/11/03/the-finance-202-wall-street-escapes-gop-tax-cutters-unscathed-so-far/59fb725730fb0468e7654095/\",\"urlToImage\":\"https://img.washingtonpost.com/pbox.php?url=https://palomaimages.washingtonpost.com/pr2/ae884734581943fdaf0db3ad9568e592-4585-3057-70-8-Botsford171102Trump21891.jpg&w=1484&op=resize&opt=1&filter=antialias&t=20170517\",\"publishedAt\":\"2017-11-03T12:27:50Z\"},{\"author\":\"Wall Street Journal\",\"title\":\"October Jobs Report: Everything You Need  to You\",\"description\":\"October Jobs Report: Everything You Need  to You\",\"url\":\"https://www.wsj.com/livecoverage/october-2017-jobs-report-analysis\",\"urlToImage\":\"https://si.wsj.net/public/resources/images/BN-UX273_jobs20_J_20170831090046.jpg\",\"publishedAt\":\"2017-11-03T12:43:42Z\"},{\"author\":\"https://www.facebook.com/hmtthomp\",\"title\":\"Rogue Twitter employee on last day of job deactivated Trump’s personal account, company says\",\"description\":\"The president relies heavily on Twitter to announce policy, lash out at adversaries and communicate with the American people.\",\"url\":\"https://www.washingtonpost.com/news/the-switch/wp/2017/11/02/trumps-twitter-account-was-temporarily-deactivated-due-to-human-error/\",\"urlToImage\":\"https://img.washingtonpost.com/rf/image_1484w/2010-2019/Wires/Videos/201711/Reuters/Images/2017-11-03T080827Z_1_OV75XF0BV_RTRMADC_0_USA-TRUMP-TWITTER.jpg?t=20170517\",\"publishedAt\":\"2017-11-03T10:16:38Z\"},{\"author\":\"https://www.facebook.com/NBCNews\",\"title\":\"Trump warns ISIS will pay a 'big price' after NYC truck attack\",\"description\":\"Trump on Twitter said ISIS was being hit 'much harder' in the last two days and blasted the suspect in the truck rampage in Manhattan as a 'degenerate animal.'\",\"url\":\"https://www.nbcnews.com/storyline/nyc-terrorist-attack/isis-claims-responsibility-nyc-truck-attack-without-evidence-n817156\",\"urlToImage\":\"https://media3.s-nbcnews.com/j/newscms/2017_44/2210666/171101-nyc-attack-truck-njs-229p_1281fc004361f1db6d2ea7eedfcea2cc.nbcnews-fp-1200-630.jpg\",\"publishedAt\":\"2017-11-03T13:02:19Z\"},{\"author\":\"\",\"title\":\"President Trump Is Going To Asia: What To Watch For At Each Stop\",\"description\":\"Trump will commence the longest foreign trip of his tenure on Friday, focusing on North Korea's nuclear threat and the U.S. trade deficit with China.\",\"url\":\"http://www.npr.org/2017/11/03/561369057/president-trump-is-going-to-asia-what-to-watch-for-at-each-stop\",\"urlToImage\":\"https://media.npr.org/assets/img/2017/11/01/ap_17187640627343_wide-c45f87701c62f020ce7b4beaf19a992bf189c7b6.jpg?s=1400\",\"publishedAt\":\"2017-11-03T09:01:09Z\"},{\"author\":\"https://www.facebook.com/PhilipRuckerWP\",\"title\":\"Trump pressures Justice Department to investigate ‘Crooked Hillary’\",\"description\":\"A president is supposed to avoid the appearance of trying to influence the Justice Department. But Trump is firing away on Twitter.\",\"url\":\"https://www.washingtonpost.com/news/post-politics/wp/2017/11/03/trump-pressures-justice-department-to-investigate-crooked-hillary/\",\"urlToImage\":\"https://img.washingtonpost.com/rf/image_1484w/2010-2019/WashingtonPost/2017/11/01/National-Politics/Images/Botsford171101Trump21760.JPG?t=20170517\",\"publishedAt\":\"2017-11-03T12:37:01Z\"},{\"author\":\"Martha Ross\",\"title\":\"Ivanka Trump dons bright pink miniskirt for Japan speech: Chic or inappropriate?\",\"description\":\"Ivanka Trump’s speech in Tokyo for the World Assembly for Women conference drew a low turnout, and her less than business-conservative miniskirted suit garnered mixed reactions.\",\"url\":\"http://www.mercurynews.com/2017/11/03/ivanka-trump-dons-bright-pink-miniskirt-for-japan-speech-chic-or-culturally-inappropriate/\",\"urlToImage\":\"http://www.mercurynews.com/wp-content/uploads/2017/11/ivankamini5.jpg?w=1024&h=669\",\"publishedAt\":\"2017-11-03T12:15:00Z\"},{\"author\":\"Hwaida Saad and Anne Barnard\",\"title\":\"ISIS Ousted From Deir al-Zour, Syrian Army Says\",\"description\":\"A pro-government alliance has reportedly driven the militants from their last foothold in a major city.\",\"url\":\"https://www.nytimes.com/2017/11/03/world/middleeast/syria-isis-deir-al-zour.html\",\"urlToImage\":\"https://static01.nyt.com/images/2017/11/04/world/04syria/04syria-facebookJumbo.jpg\",\"publishedAt\":\"2017-11-03T12:22:54Z\"},{\"author\":\"https://www.facebook.com/profile.php?id=557351048\",\"title\":\"iPhone X: First impressions from our first 3 days\",\"description\":\"Face ID. The big OLED screen. The notch. This is what it's like to use every part of the iPhone X.\",\"url\":\"https://www.cnet.com/news/iphone-x-week-one-first-impressions/\",\"urlToImage\":\"https://cnet1.cbsistatic.com/img/YRbLB1qQzUhQ_ZlP4XmbQpDAHEo=/2017/10/31/65b3bf93-440e-44cc-9097-6e8f7bbfd11f/iphone-x-fb-crop.jpg\",\"publishedAt\":\"2017-11-03T12:15:46Z\"}]}";
		String googleNewsResponse = new Utils().getHTTP(url);
		JsonParser jsonParser = new JsonParser();
		JsonObject googleNewsJson = jsonParser.parse(googleNewsResponse).getAsJsonObject();
		JsonArray articles = googleNewsJson.get("articles").getAsJsonArray();
		JsonObject article = articles.get(new Random().nextInt(9)).getAsJsonObject();
		JsonObject data = new JsonObject();
		aiResponse.setData(data);
		JsonArray contextOut = new JsonArray();
		aiResponse.setContextOut(contextOut);
		aiResponse.setDisplayText(article.get("title").getAsString());
		aiResponse.setSpeech(article.get("title").getAsString());
		aiResponse.setSource("GoogleNews");
		return aiResponse;
	}
}
