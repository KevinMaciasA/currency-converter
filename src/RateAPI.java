import java.util.HashMap;

import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import http.Http;

public class RateAPI {
  private String key;
  private String baseUrl = "https://v6.exchangerate-api.com/v6";

  RateAPI(String key) {
    this.key = key;
  }

  private String authUrl() {
    return """
        %s/%s/""".formatted(this.baseUrl, this.key);
  }

  public HashMap<String, Double> getRatesBy(String code) {
    final var M_CODE = code.toUpperCase();
    final var PATH = "latest/";
    var fullPath = authUrl() + PATH + M_CODE; // authUrl/latest/code
    var json = Http.request(fullPath);
    var gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .create();

    return gson
        .fromJson(json, ExchangeRate.class)
        .conversionRates();
  }
}
