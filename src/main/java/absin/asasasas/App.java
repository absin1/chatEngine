package absin.asasasas;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Session;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		post("/hello", (req, res) -> {
			System.err.println("Hit By GOOGLE");
			processRequest(req, res);
			return res.body();
		});
		get("/hello", (req, res) -> {
			// System.err.println("Hit By GOOGLE");
			return 1;
		});
	}

	private static void processRequest(Request req, Response res) throws IOException {
		// TODO Auto-generated method stub
		//printReqParams(req);
		//printSessionParams(req.session());
		new Action().getDialogflow(req, res);
	}

	private static void printSessionParams(Session session) {
		System.err.println("Printing session parameters on the WEBHOOK server.");
		for (String iterable_element : session.attributes()) {
			System.err.println(iterable_element + "  >>  " + session.attribute(iterable_element).toString());
		}
	}

	private static void printReqParams(Request req) {
		System.err.println("Printing request parameters on the WEBHOOK server.");
		Map<String, String> params = req.params();
		for (String iterable_element : params.keySet()) {
			System.err.println(iterable_element + "  >>  " + params.get(iterable_element));
		}
	}
}
