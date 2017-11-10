package absin.asasasas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

public class Action {
	public static void main(String[] args) throws IOException {
		// new Action().prepareGoogleNewsResponse();
		JsonObject result = new JsonParser().parse(
				"{         \"parameters\": {             \"city\": \"Rome\",              \"name\": \"Ana\"         },          \"contexts\": [],          \"resolvedQuery\": \"my name is Ana and I live in Rome\",          \"source\": \"agent\",          \"score\": 1.0,          \"speech\": \"\",          \"fulfillment\": {             \"messages\": [                 {                     \"speech\": \"Hi Ana! Nice to meet you!\",                      \"type\": 0                 }             ],              \"speech\": \"Hi Ana! Nice to meet you!\"         },          \"actionIncomplete\": false,          \"action\": \"greetings\",          \"metadata\": {             \"intentId\": \"9f41ef7c-82fa-42a7-9a30-49a93e2c14d0\",              \"webhookForSlotFillingUsed\": \"false\",              \"intentName\": \"greetings\",              \"webhookUsed\": \"true\"         }     }")
				.getAsJsonObject();
		// new Action().stringify(new Action().getTaskResponse(result, null));
		// System.err.println(new Google().search("who is einstein ".trim().replaceAll("
		// ", "+")));
		System.exit(0);
	}

	public void getDialogflow(Request req, Response res) throws IOException {
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(req.body()).getAsJsonObject();
		JsonObject result = parser.parse(o.get("result").toString()).getAsJsonObject();
		String resolvedQuery = result.get("resolvedQuery").getAsString().toLowerCase();
		JsonArray contexts = result.get("contexts").getAsJsonArray();
		JsonObject metadata = result.get("metadata").getAsJsonObject();
		String intentName = metadata.get("intentName").getAsString();
		System.out.println(resolvedQuery + " > " + contexts.toString() + " > " + intentName);

		switch (intentName) {
		case "talentify.agent.task":
			res.body();
			break;
		case "fallback":
			res.body(new Utils().stringify(new Google().search(resolvedQuery.trim().replaceAll(" ", "+"))));
			break;
		default:
			// res.body(stringify(new Action().prepareGoogleNewsResponse()));
			break;
		}

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

}
