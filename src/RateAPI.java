import java.util.HashMap;

import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import http.Http;

public class RateAPI {
  private String key;
  private String baseUrl = "https://v6.exchangerate-api.com/v6";
  private HashMap<String, String> codes = null;

  RateAPI(String key) {
    this.key = key;
  }

  private String authUrl() {
    return """
        %s/%s/""".formatted(this.baseUrl, this.key);
  }

  private RatesRecord recordfromJson(String json) {
    var gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting()
        .create();

    return gson
        .fromJson(json, RatesRecord.class);
  }

  public HashMap<String, Double> getRatesBy(String code) {
    final var M_CODE = code.toUpperCase();
    final var PATH = "latest/";
    var fullPath = authUrl() + PATH + M_CODE; // authUrl/latest/code
    var json = Http.request(fullPath);
    return recordfromJson(json).conversionRates();
  }

  private HashMap<String, String> getCodes() {
    final var PATH = "codes";
    var fullPath = authUrl() + PATH;
    var json = Http.request(fullPath);
    return recordfromJson(json).supportedCodes();
  }

  public String getCode(String currencyName) {
    if (codes == null)
      this.codes = getCodes();

    final String L_NAME = currencyName.toLowerCase();

    var result = this.codes
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue().toLowerCase().contains(L_NAME))
        .map(HashMap.Entry::getKey)
        .findFirst();

    return result.orElse(null);
  }

  public double calcExchange(String fromCode, String toCode, double amount) {
    final var PATH = "pair/";
    final var M_FROM = fromCode.toUpperCase();
    final var M_TO = toCode.toUpperCase();
    var fullPath = authUrl() + PATH + M_FROM + "/" + M_TO; // authUrl/pair/from/to
    var json = Http.request(fullPath);
    return recordfromJson(json).rate() * amount;
  }
}
