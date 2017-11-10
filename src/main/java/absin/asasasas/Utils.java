package absin.asasasas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.Gson;

public class Utils {
	public String getHTTP(String url) throws MalformedURLException, IOException, ProtocolException {
		String responze = null;
		URL obj = new URL(url);
		// System.err.println(url);
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
			// System.out.println(response.toString());
			responze = response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return responze;
	}

	public String stringify(ApiAIResponse aiResponse) {
		Gson gson = new Gson();
		String json = gson.toJson(aiResponse);
		System.err.println(json);
		return json;
	}

}
