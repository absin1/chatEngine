package absin.asasasas;

import java.io.IOException;
import java.util.ArrayList;

public class TestWebHook {
	public static void main(String[] args) throws IOException {
		//testGoogleSearch();
		String response = "Narendra Damodardas Modi (Gujarati: [ˈnəɾeːnd̪rə d̪aːmoːd̪əɾˈd̪aːs ˈmoːd̪iː] ( listen), born 17 September 1950) is an Indian politician who is the 14th and current Prime Minister of India, in office since May 2014.";
		
		System.err.println(response.replaceAll("\\("+".*"+"\\)", ""));
	}

	private static void testGoogleSearch() throws IOException {
		ArrayList<String> q = new ArrayList<>();
		q.add("how+does+internet+work");
		q.add("who+is+alaudin+khilji");
		q.add("how+does+hibernate+work");
		q.add("what+is+java");
		q.add("when+was+buddha+born");
		q.add("who+is+christopher+columbus");
		for (String string : q) {
			ApiAIResponse googleSearchResponse = new Google().search(string);
			System.out.println(string + " >> " + googleSearchResponse.getSpeech() + "\n");
		}
	}
}
