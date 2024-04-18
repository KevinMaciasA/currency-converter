import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class UI {
  private final int EXIT_NUMBER = 9;
  private final String EXIT_CODE = "Exit";
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
        %d. Exit

        Enter the corresponding number to proceed:""".formatted(favsString, EXIT_NUMBER);

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
        Result: %.4f""".formatted(result);
    System.out.println(screen);
    scanner.nextLine(); // wait till input
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

    var option = options[input - 1];
    history.push(option);
    return option;
  }

  public String exitCode() {
    return EXIT_CODE;
  }

  public void pause() {
    scanner.nextLine();
  }
}
