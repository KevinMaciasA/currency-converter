import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.List;

public class UI {
  private final int HISTORY_NUMBER = 8;
  private final int EXIT_NUMBER = 9;
  private final String EXIT_CODE = "Exit";
  private final String HISTORY_CODE = "History";
  private Scanner scanner = new Scanner(System.in);
  private Stack<String> history = new Stack<>();

  public void greetings() {
    System.out.println("""

        Welcome to Currency Exchange!""");
  }

  public String mainMenu() {
    var favs = CurrencyCodes.favs;
    var favsString = CurrencyCodes.toString(favs);

    var screen = """

        Please select your starting currency from the menu below:

        %s

        %d. History
        %d. Exit

        Enter the corresponding number to proceed:""".formatted(
        favsString,
        HISTORY_NUMBER,
        EXIT_NUMBER);

    System.out.println(screen);

    return getCode(favs);
  }

  public String selectMenu() {
    var favs = Arrays
        .stream(CurrencyCodes.favs)
        .filter(curr -> !curr.equals(history.peek()))
        .toArray(String[]::new);
    var favsString = CurrencyCodes.toString(favs);

    var screen = """

        Now, select your target currency:

        %s

        %d. Go back

        Enter the corresponding number to proceed:""".formatted(favsString, EXIT_NUMBER);

    System.out.println(screen);
    return getCode(favs);
  }

  public double exchangeMenu() {
    var screen = """
        Amount: """;
    System.out.println(screen);
    return scanner.nextDouble();
  }

  public void result(double result) {
    var screen = """
        Result:
        %.4f$""".formatted(result);
    System.out.println(screen);
  }

  public void history(List<QueryResult> dataList) {
    String h = dataList
        .stream()
        .map(element -> element.toString())
        .collect(Collectors.joining("\n"));

    var screen = """
        *** History ***

        %s.""".formatted(h);

    System.out.println(screen);
  }

  public void exit() {
    System.out.println("""

        Have a nice day!
        """);
    scanner.close();
  }

  public String getCode(String[] options) {
    var input = scanner.nextInt();

    if (input == EXIT_NUMBER)
      return EXIT_CODE;

    if (input == HISTORY_NUMBER)
      return HISTORY_CODE;

    try {
      var option = options[input - 1];
      history.push(option);
      return option;
    } catch (ArrayIndexOutOfBoundsException e) {
      pause("Invalid option, try again...");
      return null;
    }
  }

  public String exitCode() {
    return EXIT_CODE;
  }

  public String historyCode() {
    return HISTORY_CODE;
  }

  public void pause() {
    pause("Press Enter to continue...");
  }

  public void pause(String message) {
    System.out.println("""
        %s""".formatted(message));
    scanner.nextLine();
    scanner.nextLine();
  }
}
