import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

public record RatesRecord(
    HashMap<String, Double> conversionRates,
    HashMap<String, String> supportedCodes,
    @SerializedName("conversion_rate") double rate) {
}
