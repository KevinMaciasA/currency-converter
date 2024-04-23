import java.time.LocalDateTime;

public class QueryResult {
  private LocalDateTime date;
  private String from;
  private String to;
  private double amount;
  private double result;

  QueryResult(String from, String to, double amount, double result) {
    this.date = LocalDateTime.now();
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.result = result;
  }

  QueryResult(LocalDateTime date, String from, String to, double amount, double result) {
    this.date = LocalDateTime.now();
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.result = result;
  }

  public String[] data() {
    return new String[] {
        date.toString(),
        from,
        to,
        Double.toString(amount),
        Double.toString(result) };
  }

  public static QueryResult fromString(String row) {
    var array = row.split(",");

    return new QueryResult(
        LocalDateTime.parse(array[0]),
        array[1],
        array[2],
        Double.parseDouble(array[3]),
        Double.parseDouble(array[4]));
  }

  @Override
  public String toString() {
    return """
        Date: %s,
        From: %s,
        To: %s,
        Amount: %.4f,
        Result: %.4f
        """.formatted(date, from, to, amount, result);
  }
}
