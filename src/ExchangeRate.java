import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

public record ExchangeRate(
    @SerializedName("conversion_rates") HashMap<String, Double> conversionRates) {
}
