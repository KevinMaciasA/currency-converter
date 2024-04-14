package http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public abstract class Http {
  public static String request(String url) {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(URI.create(url))
          .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();

    } catch (Exception e) {
      e.printStackTrace();
      return e.getMessage();
    }
  }
}
