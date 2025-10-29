package fps_rpg.game;

/**
 * Simple IO abstraction to separate console I/O from game logic (SRP, DIP).
 */
public interface ConsoleIO {
    String readLine();
    void print(String s);
    void println(String s);
}
