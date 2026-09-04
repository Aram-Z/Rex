package userInterface;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getCommand(){
        return scanner.nextLine();
    }

    @Override
    public void printMessage(String message){
        System.out.println(message);
    }

    @Override
    public void invalidCommand(){
        System.out.println("Invalid command! Try again.");
    }

    @Override
    public void end(){
        scanner.close();
    }

}
