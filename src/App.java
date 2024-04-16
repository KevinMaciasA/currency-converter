import env.Env;

public class App {
    public static void main(String[] args) throws Exception {
        var key = Env.read();
        var api = new RateAPI(key);
        var result = api.getRatesBy("USD");
        System.out.println(result.get("EUR"));
    }
}
