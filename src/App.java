import env.Env;

import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class App {
    private static enum State {
        MAIN,
        SELECTED,
        EXCHANGE,
        HISTORY,
        EXIT
    }

    private static UI ui = new UI();
    private static State currentState;
    private static RateAPI api;
    private static String fromCode = null;
    private static String toCode = null;
    private static Recorder recorder = null;
    private static LinkedList<QueryResult> history = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        var key = Env.read();
        api = new RateAPI(key);
        currentState = State.MAIN;
        initHistory();
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
                    break;
                case State.HISTORY:
                    historyState();
                    break;
                default:
                    break;
            }
        }

        logHistory();
        ui.exit();
    }

    private static void mainState() {
        var result = ui.mainMenu();

        if (result == null)
            return;

        if (result == ui.exitCode()) {
            currentState = State.EXIT;
        } else if (result == ui.historyCode()) {
            currentState = State.HISTORY;
        } else {
            fromCode = result;
            currentState = State.SELECTED;
        }
    }

    private static void selectedState() {
        var result = ui.selectMenu();

        if (result == null)
            return;

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
        history.push(new QueryResult(fromCode, toCode, amount, result));
        currentState = State.MAIN;
    }

    private static void initHistory() {
        var defaultFileName = "data.csv";
        try {
            var r = new Recorder(defaultFileName);
            r.readData()
                    .stream()
                    .map(str -> QueryResult.fromString(str))
                    .forEach(q -> history.push(q)); // populate history
            recorder = r;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void logHistory() {
        if (recorder == null)
            return;

        var historyList = history
                .stream()
                .limit(Recorder.maxRows)
                .map(q -> q.data())
                .collect(Collectors.toList());
        recorder.write(historyList);
    }

    private static void historyState() {
        ui.history(history);
        ui.pause();
        currentState = State.MAIN;
    }
}
