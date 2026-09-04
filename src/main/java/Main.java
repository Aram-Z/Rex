import userInterface.ConsoleUserInterface;
import userInterface.UserInterface;

public class Main {
    public static void main(String[] args) {
        ConsoleUserInterface userInterface = new ConsoleUserInterface();
        Engine engine = new Engine(userInterface);
        engine.run();
    }
}
