import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CurrencyCodes {
  public static String[] favs = { "USD", "EUR" };

  public static String toString(String[] codes) {
    return IntStream
        .range(0, codes.length)
        .mapToObj(index -> (index + 1) + ". " + codes[index])
        .collect(Collectors.joining("\n"));
  }
}

// public enum CurrencyCodes {
// USD,
// EUR,
// }
