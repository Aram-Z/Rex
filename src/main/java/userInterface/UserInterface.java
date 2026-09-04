package userInterface;

public interface UserInterface {
    String getCommand();

    void printMessage(String message);

    void invalidCommand();

    void end();
}
