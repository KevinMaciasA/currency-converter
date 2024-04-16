import env.Env;

public class App {
    public static void main(String[] args) throws Exception {
        var key = Env.read();
        var api = new RateAPI(key);
        var USDRates = api.getRatesBy("USD");
        System.out.println("Rate: " + USDRates.get("EUR"));
        var result = api.calcExchange("USD", "EUR", 10);
        System.out.println("Exchange: " + result);
    }
}
