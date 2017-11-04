package absin.asasasas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Response;

public class Action {
	public static void main(String[] args) throws IOException {

		String body = "\n" + "{\n" + "    \"lang\": \"en\", \n" + "    \"status\": {\n"
				+ "        \"errorType\": \"success\", \n" + "        \"code\": 200\n" + "    }, \n"
				+ "    \"timestamp\": \"2017-02-09T16:06:01.908Z\", \n" + "    \"sessionId\": \"1486656220806\", \n"
				+ "    \"result\": {\n" + "        \"parameters\": {\n" + "            \"city\": \"Rome\", \n"
				+ "            \"name\": \"Ana\"\n" + "        }, \n" + "        \"contexts\": [], \n"
				+ "        \"resolvedQuery\": \"my name is Ana and I live in Rome\", \n"
				+ "        \"source\": \"agent\", \n" + "        \"score\": 1.0, \n" + "        \"speech\": \"\", \n"
				+ "        \"fulfillment\": {\n" + "            \"messages\": [\n" + "                {\n"
				+ "                    \"speech\": \"Hi Ana! Nice to meet you!\", \n"
				+ "                    \"type\": 0\n" + "                }\n" + "            ], \n"
				+ "            \"speech\": \"Hi Ana! Nice to meet you!\"\n" + "        }, \n"
				+ "        \"actionIncomplete\": false, \n" + "        \"action\": \"greetings\", \n"
				+ "        \"metadata\": {\n" + "            \"intentId\": \"9f41ef7c-82fa-42a7-9a30-49a93e2c14d0\", \n"
				+ "            \"webhookForSlotFillingUsed\": \"false\", \n"
				+ "            \"intentName\": \"fallback\", \n" + "            \"webhookUsed\": \"true\"\n"
				+ "        }\n" + "    }, \n" + "    \"id\": \"ab30d214-f4bb-4cdd-ae36-31caac7a6693\", \n"
				+ "    \"originalRequest\": {\n" + "        \"source\": \"google\", \n" + "        \"data\": {\n"
				+ "            \"inputs\": [\n" + "                {\n" + "                    \"raw_inputs\": [\n"
				+ "                        {\n"
				+ "                            \"query\": \"my name is Ana and I live in Rome\", \n"
				+ "                            \"input_type\": 2\n" + "                        }\n"
				+ "                    ], \n" + "                    \"intent\": \"assistant.intent.action.TEXT\", \n"
				+ "                    \"arguments\": [\n" + "                        {\n"
				+ "                            \"text_value\": \"my name is Ana and I live in Rome\", \n"
				+ "                            \"raw_text\": \"my name is Ana and I live in Rome\", \n"
				+ "                            \"name\": \"text\"\n" + "                        }\n"
				+ "                    ]\n" + "                }\n" + "            ], \n" + "            \"user\": {\n"
				+ "                \"user_id\": \"PuQndWs1OMjUYwVJMYqwJv0/KT8satJHAUQGiGPDQ7A=\"\n"
				+ "            }, \n" + "            \"conversation\": {\n"
				+ "                \"conversation_id\": \"1486656220806\", \n" + "                \"type\": 2, \n"
				+ "                \"conversation_token\": \"[]\"\n" + "            }\n" + "        }\n" + "    }\n"
				+ "} ";
		new Action().getDialogflow(body, null);
	}

	public void getDialogflow(String body, Response res) throws IOException {

		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(body).getAsJsonObject();
		JsonObject result = parser.parse(o.get("result").toString()).getAsJsonObject();
		String resolvedQuery = result.get("resolvedQuery").getAsString().toLowerCase();
		JsonArray contexts = result.get("contexts").getAsJsonArray();
		JsonObject metadata = result.get("metadata").getAsJsonObject();
		String intentName = metadata.get("intentName").getAsString();
		// read some text in the text variable
		System.out.println(resolvedQuery);
		System.out.println(contexts.toString());
		System.out.println(intentName);
		HashSet<String> queryAsSetCleaned = new HashSet<>();

		if (intentName.equalsIgnoreCase("fallback")) {
			queryAsSetCleaned = removeStopWords(resolvedQuery);
			res.body(prepareActualResponse(queryAsSetCleaned));
			// System.err.println(Arrays.toString(queryAsSetCleaned.toArray()));
			// String wikiPedia = getWikiPedia(queryAsSetCleaned);
			// res.body(prepareStaticResponse(""));
		}

	}

	private String prepareActualResponse(HashSet<String> queryAsSetCleaned)
			throws MalformedURLException, ProtocolException, IOException {
		ApiAIResponse aiResponse = new ApiAIResponse();
		String url = "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=fd3b8cc9043b46da91f47f5581b63581";
		String responze = "{\"status\":\"ok\",\"source\":\"google-news\",\"sortBy\":\"top\",\"articles\":[{\"author\":null,\"title\":\"This RSS feed URL is deprecated\",\"description\":\"Comprehensive up-to-date news coverage, aggregated from sources all over the world by Google News.\",\"url\":\"https://news.google.com/headlines\",\"urlToImage\":null,\"publishedAt\":\"2017-11-03T13:15:08Z\"},{\"author\":\"https://www.facebook.com/tory.newmyer\",\"title\":\"Analysis | The Finance 202: Wall Street escapes GOP tax cutters unscathed, so far\",\"description\":\"But stay tuned.\",\"url\":\"https://www.washingtonpost.com/news/powerpost/paloma/the-finance-202/2017/11/03/the-finance-202-wall-street-escapes-gop-tax-cutters-unscathed-so-far/59fb725730fb0468e7654095/\",\"urlToImage\":\"https://img.washingtonpost.com/pbox.php?url=https://palomaimages.washingtonpost.com/pr2/ae884734581943fdaf0db3ad9568e592-4585-3057-70-8-Botsford171102Trump21891.jpg&w=1484&op=resize&opt=1&filter=antialias&t=20170517\",\"publishedAt\":\"2017-11-03T12:27:50Z\"},{\"author\":\"Wall Street Journal\",\"title\":\"October Jobs Report: Everything You Need  to You\",\"description\":\"October Jobs Report: Everything You Need  to You\",\"url\":\"https://www.wsj.com/livecoverage/october-2017-jobs-report-analysis\",\"urlToImage\":\"https://si.wsj.net/public/resources/images/BN-UX273_jobs20_J_20170831090046.jpg\",\"publishedAt\":\"2017-11-03T12:43:42Z\"},{\"author\":\"https://www.facebook.com/hmtthomp\",\"title\":\"Rogue Twitter employee on last day of job deactivated Trump’s personal account, company says\",\"description\":\"The president relies heavily on Twitter to announce policy, lash out at adversaries and communicate with the American people.\",\"url\":\"https://www.washingtonpost.com/news/the-switch/wp/2017/11/02/trumps-twitter-account-was-temporarily-deactivated-due-to-human-error/\",\"urlToImage\":\"https://img.washingtonpost.com/rf/image_1484w/2010-2019/Wires/Videos/201711/Reuters/Images/2017-11-03T080827Z_1_OV75XF0BV_RTRMADC_0_USA-TRUMP-TWITTER.jpg?t=20170517\",\"publishedAt\":\"2017-11-03T10:16:38Z\"},{\"author\":\"https://www.facebook.com/NBCNews\",\"title\":\"Trump warns ISIS will pay a 'big price' after NYC truck attack\",\"description\":\"Trump on Twitter said ISIS was being hit 'much harder' in the last two days and blasted the suspect in the truck rampage in Manhattan as a 'degenerate animal.'\",\"url\":\"https://www.nbcnews.com/storyline/nyc-terrorist-attack/isis-claims-responsibility-nyc-truck-attack-without-evidence-n817156\",\"urlToImage\":\"https://media3.s-nbcnews.com/j/newscms/2017_44/2210666/171101-nyc-attack-truck-njs-229p_1281fc004361f1db6d2ea7eedfcea2cc.nbcnews-fp-1200-630.jpg\",\"publishedAt\":\"2017-11-03T13:02:19Z\"},{\"author\":\"\",\"title\":\"President Trump Is Going To Asia: What To Watch For At Each Stop\",\"description\":\"Trump will commence the longest foreign trip of his tenure on Friday, focusing on North Korea's nuclear threat and the U.S. trade deficit with China.\",\"url\":\"http://www.npr.org/2017/11/03/561369057/president-trump-is-going-to-asia-what-to-watch-for-at-each-stop\",\"urlToImage\":\"https://media.npr.org/assets/img/2017/11/01/ap_17187640627343_wide-c45f87701c62f020ce7b4beaf19a992bf189c7b6.jpg?s=1400\",\"publishedAt\":\"2017-11-03T09:01:09Z\"},{\"author\":\"https://www.facebook.com/PhilipRuckerWP\",\"title\":\"Trump pressures Justice Department to investigate ‘Crooked Hillary’\",\"description\":\"A president is supposed to avoid the appearance of trying to influence the Justice Department. But Trump is firing away on Twitter.\",\"url\":\"https://www.washingtonpost.com/news/post-politics/wp/2017/11/03/trump-pressures-justice-department-to-investigate-crooked-hillary/\",\"urlToImage\":\"https://img.washingtonpost.com/rf/image_1484w/2010-2019/WashingtonPost/2017/11/01/National-Politics/Images/Botsford171101Trump21760.JPG?t=20170517\",\"publishedAt\":\"2017-11-03T12:37:01Z\"},{\"author\":\"Martha Ross\",\"title\":\"Ivanka Trump dons bright pink miniskirt for Japan speech: Chic or inappropriate?\",\"description\":\"Ivanka Trump’s speech in Tokyo for the World Assembly for Women conference drew a low turnout, and her less than business-conservative miniskirted suit garnered mixed reactions.\",\"url\":\"http://www.mercurynews.com/2017/11/03/ivanka-trump-dons-bright-pink-miniskirt-for-japan-speech-chic-or-culturally-inappropriate/\",\"urlToImage\":\"http://www.mercurynews.com/wp-content/uploads/2017/11/ivankamini5.jpg?w=1024&h=669\",\"publishedAt\":\"2017-11-03T12:15:00Z\"},{\"author\":\"Hwaida Saad and Anne Barnard\",\"title\":\"ISIS Ousted From Deir al-Zour, Syrian Army Says\",\"description\":\"A pro-government alliance has reportedly driven the militants from their last foothold in a major city.\",\"url\":\"https://www.nytimes.com/2017/11/03/world/middleeast/syria-isis-deir-al-zour.html\",\"urlToImage\":\"https://static01.nyt.com/images/2017/11/04/world/04syria/04syria-facebookJumbo.jpg\",\"publishedAt\":\"2017-11-03T12:22:54Z\"},{\"author\":\"https://www.facebook.com/profile.php?id=557351048\",\"title\":\"iPhone X: First impressions from our first 3 days\",\"description\":\"Face ID. The big OLED screen. The notch. This is what it's like to use every part of the iPhone X.\",\"url\":\"https://www.cnet.com/news/iphone-x-week-one-first-impressions/\",\"urlToImage\":\"https://cnet1.cbsistatic.com/img/YRbLB1qQzUhQ_ZlP4XmbQpDAHEo=/2017/10/31/65b3bf93-440e-44cc-9097-6e8f7bbfd11f/iphone-x-fb-crop.jpg\",\"publishedAt\":\"2017-11-03T12:15:46Z\"}]}";
		String googleNewsResponse = extracted(url, responze);
		//String googleNewsResponse = "{\"status\":\"ok\",\"source\":\"google-news\",\"sortBy\":\"top\",\"articles\":[{\"author\":null,\"title\":\"George H.W. Bush calls Trump a 'blowhard' in new book: 'I don't like him'\",\"description\":\"Former President George H.W. Bush says he is not too excited about blowhard President Trump and confirmed in a new book that he voted for Hillary Clinton.\",\"url\":\"http://www.foxnews.com/politics/2017/11/04/george-h-w-bush-calls-trump-blowhard-in-new-book-dont-like-him.html\",\"urlToImage\":\"//a57.foxnews.com/images.foxnews.com/content/dam/fox-news/images/2017/10/24/yj.jpg.img.png/0/0/1508907581904.png?ve=1\",\"publishedAt\":\"2017-11-04T13:04:49Z\"},{\"author\":\"Analysis by Z. Byron Wolf, CNN\",\"title\":\"Could Sanders have won primary that wasn't 'rigged'?\",\"description\":\"President Donald Trump isn't the only one obsessed with the 2016 election. Democrats will forever be wondering what might have been if Hillary Clinton had won.\",\"url\":\"http://www.cnn.com/2017/11/04/politics/bernie-sanders-2016-election-donna-brazile/index.html\",\"urlToImage\":\"http://cdn.cnn.com/cnnnext/dam/assets/170109150633-bernie-sanders-hillary-clinton-0712-super-tease.jpg\",\"publishedAt\":\"2017-11-04T12:52:31Z\"},{\"author\":\"David Ng, Meg James\",\"title\":\"Netflix has a mess on its hands with the collapse of 'House of Cards'\",\"description\":\"Kevin Spacey, who stars in the series and serves as executive producer, faces accusations of sexual harassment and assault from multiple men. The fate of the show's sixth season is being decided.\",\"url\":\"http://www.latimes.com/business/hollywood/la-fi-ct-netflix-kevin-spacey-20171104-story.html\",\"urlToImage\":\"http://www.trbimg.com/img-59fd4058/turbine/la-fi-ct-netflix-kevin-spacey-20171104\",\"publishedAt\":\"2017-11-04T10:00:41Z\"},{\"author\":\"Zeina Karam / AP\",\"title\":\"Lebanese Prime Minister Suggests He Fears for His Life During Resignation\",\"description\":\"\",\"url\":\"http://time.com/5010261/lebanese-prime-minister-resigns/\",\"urlToImage\":\"https://timedotcom.files.wordpress.com/2017/11/lebanese-prime-minister.jpg?quality=85\",\"publishedAt\":\"2017-11-04T14:07:40Z\"},{\"author\":\"Rich Schapiro\",\"title\":\"Martin Luther King had love child, bedded Joan Baez: JFK files\",\"description\":\"The remark from Soviet Consul Pavel Yatskov in Mexico City appears in the latest batch of JFK files released Friday.\",\"url\":\"http://www.nydailynews.com/news/national/soviet-diplomat-oswald-nervous-kill-jfk-cia-files-article-1.3609719\",\"urlToImage\":\"http://assets.nydailynews.com/polopoly_fs/1.3610266.1509774794!/img/httpImage/image.jpg_gen/derivatives/landscape_1200/u1530573.jpg\",\"publishedAt\":\"2017-11-04T02:52:00Z\"},{\"author\":\"https://www.facebook.com/politico/\",\"title\":\"Dems’ plan to tank Trump’s tax bill\",\"description\":\"Democrats hope to replicate their successful Obamacare strategy — but it  won’t be easy.\",\"url\":\"https://www.politico.com/story/2017/11/04/trump-gop-tax-bill-democrats-244529\",\"urlToImage\":\"https://static.politico.com/82/14/02395f1f44bd94e7e824e2d49924/171102-nancy-pelosi-gty-1160.jpg\",\"publishedAt\":\"2017-11-04T11:13:51Z\"},{\"author\":\"Mark Mazzetti and Adam Goldman\",\"title\":\"Trump Campaign Adviser Met With Russian Officials in 2016\",\"description\":\"Carter Page, a former foreign policy adviser to the Trump campaign, told House investigators he met with Russian government officials during a July 2016 trip to Moscow. He has long denied doing so.\",\"url\":\"https://www.nytimes.com/2017/11/03/us/politics/trump-campaign-page-russian.html\",\"urlToImage\":\"https://static01.nyt.com/images/2017/11/04/us/politics/04dc-page/04dc-page-facebookJumbo.jpg\",\"publishedAt\":\"2017-11-04T09:55:48Z\"},{\"author\":\"https://www.facebook.com/mikerosenwald\",\"title\":\"‘Termination of chaos’: How daylight saving solved America’s clock craziness\",\"description\":\"The Uniform Time Act of 1966, signed into law by President Johnson, was designed to end the country's 'Babel of contradictory clocks.'\",\"url\":\"https://www.washingtonpost.com/news/retropolis/wp/2017/11/04/termination-of-chaos-how-daylight-saving-solved-americas-clock-craziness/\",\"urlToImage\":\"https://img.washingtonpost.com/pbox.php?url=http://img.washingtonpost.com/news/retropolis/wp-content/uploads/sites/62/2017/11/clocks.jpg&w=1484&op=resize&opt=1&filter=antialias&t=20170517\",\"publishedAt\":\"2017-11-04T11:07:18Z\"},{\"author\":\"Brad Plumer\",\"title\":\"A Climate Science Report That Changes Minds? Don’t Bet on It\",\"description\":\"Opinions on climate change largely follow partisan lines and a dire new report is unlikely to reset the political debate.\",\"url\":\"https://www.nytimes.com/2017/11/04/climate/trump-climate-change-report.html\",\"urlToImage\":\"https://static01.nyt.com/images/2017/11/05/us/05cli-assess-1/05cli-assess-1-facebookJumbo.jpg\",\"publishedAt\":\"2017-11-04T14:01:56Z\"},{\"author\":\"Doug Stanglin\",\"title\":\"JFK assassination files: Oswald meets KGB officer at Soviet embassy in Mexico City\",\"description\":\"The National Archives releases more than 600 new files, mostly from the CIA.\",\"url\":\"https://www.usatoday.com/story/news/2017/11/04/jfk-assassination-files-documenew-release-covers-oswald-calls-soveit-embassy-mexico-city-soviet-spie/832138001/\",\"urlToImage\":\"https://www.gannett-cdn.com/-mm-/4924e42ac38afb6c5f67a409ce97dd841b6ef91e/c=0-214-4344-2668&r=x1683&c=3200x1680/local/-/media/2017/10/28/USATODAY/USATODAY/636447961874073930-AP-Trump-JFK-Files.jpg\",\"publishedAt\":\"2017-11-04T13:41:38Z\"}]}\n";
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
		Gson gson = new Gson();
		String json = gson.toJson(aiResponse);
		System.err.println(json);
		return json;

	}

	private String extracted(String url, String responze) throws MalformedURLException, IOException, ProtocolException {
		URL obj = new URL(url);
		System.err.println(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			responze = response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return responze;
	}

	private String prepareStaticResponse(String wikiPedia) {
		String body = "{\n"
				+ "\"speech\": \"Barack Hussein Obama II was the 44th and current President of the United States.\",\n"
				+ "\"displayText\": \"Barack Hussein Obama II was the 44th and current President of the United States, and the first African American to hold the office. Born in Honolulu, Hawaii, Obama is a graduate of Columbia University   and Harvard Law School, where \",\n"
				+ "\"data\": {},\n" + "\"contextOut\": [],\n" + "\"source\": \"DuckDuckGo\"\n" + "}";
		// TODO Auto-generated method stub
		return body;
	}

	private String getWikiPedia(HashSet<String> queryAsSetCleaned) throws IOException {
		String responze = "";
		String string = Arrays.toString(queryAsSetCleaned.toArray());
		try {
			string = string.replaceAll(", ", "%20");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			string = string.replaceAll("\\[", "").replaceAll("\\]", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String url = "https://en.wikipedia.org/w/api.php?action=opensearch&search=" + string
				+ "&limit=1&namespace=0&format=json";
		URL obj = new URL(url);
		System.err.println(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			responze = response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return responze;
	}

	private HashSet<String> removeStopWords(String resolvedQuery) {
		String stopwords = "a,about,above,across,after,again,against,all,almost,alone,along,already,also,although,always,among,an,and,another,any,anybody,anyone,anything,anywhere,are,area,areas,around,as,ask,asked,asking,asks,at,away,b,back,backed,backing,backs,be,became,because,become,becomes,been,before,began,behind,being,beings,best,better,between,big,both,but,by,c,came,can,cannot,case,cases,certain,certainly,clear,clearly,come,could,d,did,differ,different,differently,do,does,done,down,down,downed,downing,downs,during,e,each,early,either,end,ended,ending,ends,enough,even,evenly,ever,every,everybody,everyone,everything,everywhere,f,face,faces,fact,facts,far,felt,few,find,finds,first,for,four,from,full,fully,further,furthered,furthering,furthers,g,gave,general,generally,get,gets,give,given,gives,go,going,good,goods,got,great,greater,greatest,group,grouped,grouping,groups,h,had,has,have,having,he,her,here,herself,high,high,high,higher,highest,him,himself,his,how,however,i,if,important,in,interest,interested,interesting,interests,into,is,it,its,itself,j,just,k,keep,keeps,kind,knew,know,known,knows,l,large,largely,last,later,latest,least,less,let,lets,like,likely,long,longer,longest,m,made,make,making,man,many,may,me,member,members,men,might,more,most,mostly,mr,mrs,much,must,my,myself,n,necessary,need,needed,needing,needs,never,new,new,newer,newest,next,no,nobody,non,noone,not,nothing,now,nowhere,number,numbers,o,of,off,often,old,older,oldest,on,once,one,only,open,opened,opening,opens,or,order,ordered,ordering,orders,other,others,our,out,over,p,part,parted,parting,parts,per,perhaps,place,places,point,pointed,pointing,points,possible,present,presented,presenting,presents,problem,problems,put,puts,q,quite,r,rather,really,right,right,room,rooms,s,said,same,saw,say,says,second,seconds,see,seem,seemed,seeming,seems,sees,several,shall,she,should,show,showed,showing,shows,side,sides,since,small,smaller,smallest,so,some,somebody,someone,something,somewhere,state,states,still,still,such,sure,t,take,taken,than,that,the,their,them,then,there,therefore,these,they,thing,things,think,thinks,this,those,though,thought,thoughts,three,through,thus,to,today,together,too,took,toward,turn,turned,turning,turns,two,u,under,until,up,upon,us,use,used,uses,v,very,w,want,wanted,wanting,wants,was,way,ways,we,well,wells,went,were,what,when,where,whether,which,while,who,whole,whose,why,will,with,within,without,work,worked,working,works,would,x,y,year,years,yet,you,young,younger,youngest,your,yours,z";
		HashSet<String> stops = new HashSet<String>();
		getStopWords(stopwords, stops);
		stopwords = "a,about,above,after,again,against,all,am,an,and,any,are,aren't,as,at,be,because,been,before,being,below,between,both,but,by,can't,cannot,could,couldn't,did,didn't,do,does,doesn't,doing,don't,down,during,each,few,for,from,further,had,hadn't,has,hasn't,have,haven't,having,he,he'd,he'll,he's,her,here,here's,hers,herself,him,himself,his,how,how's,i,i'd,i'll,i'm,i've,if,in,into,is,isn't,it,it's,its,itself,let's,me,more,most,mustn't,my,myself,no,nor,not,of,off,on,once,only,or,other,ought,our,ours,ourselves,out,over,own,same,shan't,she,she'd,she'll,she's,should,shouldn't,so,some,such,than,that,that's,the,their,theirs,them,themselves,then,there,there's,these,they,they'd,they'll,they're,they've,this,those,through,to,too,under,until,up,very,was,wasn't,we,we'd,we'll,we're,we've,were,weren't,what,what's,when,when's,where,where's,which,while,who,who's,whom,why,why's,with,won't,would,wouldn't,you,you'd,you'll,you're,you've,your,yours,yourself,yourselves";
		getStopWords(stopwords, stops);
		HashSet<String> queryAsSet = getQueryAsSet(resolvedQuery);
		HashSet<String> queryAsSetCleaned = new HashSet<>();
		for (String string : queryAsSet) {
			if (!stopwords.contains(string)) {
				queryAsSetCleaned.add(string);
			}
		}
		return queryAsSetCleaned;
	}

	private HashSet<String> getQueryAsSet(String resolvedQuery) {
		HashSet<String> query = new HashSet<>();
		for (String string : resolvedQuery.split(" ")) {
			query.add(string);
		}
		return query;
	}

	private HashSet<String> getStopWords(String stopwords, HashSet<String> stops) {
		for (String string : stopwords.split(",")) {
			stops.add(string.trim().toLowerCase());
		}
		return stops;
	}

}
