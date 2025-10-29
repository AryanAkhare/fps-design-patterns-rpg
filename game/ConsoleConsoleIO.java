package fps_rpg.game;

import java.util.Scanner;

public class ConsoleConsoleIO implements ConsoleIO {
    private final Scanner scanner;
    public ConsoleConsoleIO() { this.scanner = new Scanner(System.in); }
    @Override public String readLine() { return scanner.nextLine(); }
    @Override public void print(String s) { System.out.print(s); }
    @Override public void println(String s) { System.out.println(s); }
}
