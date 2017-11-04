package absin.asasasas;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;

import spark.Response;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		post("/hello", (req, res) -> {
			System.err.println("Hit By GOOGLE");
			processRequest(req.body(), res);
			return res.body();
		});
		get("/hello", (req, res) -> {
			// System.err.println("Hit By GOOGLE");
			return 1;
		});
	}

	private static void processRequest(String body, Response res) throws IOException {
		// TODO Auto-generated method stub
		new Action().getDialogflow(body, res);
	}
}
