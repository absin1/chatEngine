package absin.asasasas;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiAIResponse {
	String speech;
	String displayText;
	JsonObject data;
	JsonArray contextOut;
	String source;

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}

	public JsonArray getContextOut() {
		return contextOut;
	}

	public void setContextOut(JsonArray contextOut) {
		this.contextOut = contextOut;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
