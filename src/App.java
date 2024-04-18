import env.Env;

public class App {
    private static enum State {
        MAIN,
        SELECTED,
        EXCHANGE,
        EXIT
    }

    private static UI ui = new UI();
    private static State currentState;
    private static RateAPI api;
    private static String fromCode = null;
    private static String toCode = null;

    public static void main(String[] args) throws Exception {
        var key = Env.read();
        api = new RateAPI(key);
        currentState = State.MAIN;
        ui.greetings();

        while (currentState != State.EXIT) {
            switch (currentState) {
                case State.MAIN:
                    mainState();
                    break;
                case State.SELECTED:
                    selectedState();
                    break;
                case State.EXCHANGE:
                    exchangeState();
                default:
                    break;
            }
        }

        ui.exit();
    }

    private static void mainState() {
        var result = ui.mainMenu();

        if (result == ui.exitCode()) {
            currentState = State.EXIT;
        } else {
            fromCode = result;
            currentState = State.SELECTED;
        }
    }

    private static void selectedState() {
        var result = ui.selectMenu();

        if (result == ui.exitCode()) {
            currentState = State.MAIN;
        } else {
            toCode = result;
            currentState = State.EXCHANGE;
        }
    }

    private static void exchangeState() {
        var amount = ui.exchangeMenu();
        var result = api.calcExchange(fromCode, toCode, amount);
        ui.result(result);
        ui.pause();
        currentState = State.MAIN;
    }
}
