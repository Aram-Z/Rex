import animal.Animal;
import animal.Cat;
import animal.Dog;
import center.AdoptionCenter;
import userInterface.UserInterface;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.plaf.synth.Region.SEPARATOR;

public class Engine {
    private boolean isRunning;
    private final UserInterface userInterface;
    private Map<String, AdoptionCenter> adoptionCenters = new HashMap<>();
    private static final String SEPARATOR = "\n";

    public Engine(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.isRunning = true;
    }

    public void run() {
        while (isRunning) {
            process(userInterface.getCommand());
        }
        userInterface.end();
    }

    protected void process(String command) {
        String[] commandList = commandSplitter(command);
        switch (commandList[0]) {
            case "RegisterAdoptionCenter":
                AdoptionCenter adoptionCenter = new AdoptionCenter(commandList[1]);
                adoptionCenters.put(commandList[1], adoptionCenter);
                break;

            case "RegisterDog":
                registerDog(commandList);

                break;
            case "RegisterCat":
                registerCat(commandList);
                break;
            case "SendForCleansing":
                SendForCleansing(commandList[4]);

                break;
            case "Rest":

                break;
            case "PrintStatus":
                printStatus();
                break;
            case "Quit":
                stop();
                break;
            default:
                invalidCommand();
        }
    }

    private void sendForCleansing(String adoptionCenterName) {
        AdoptionCenter adoptionCenter = adoptionCenters.get(adoptionCenterName);

        if (adoptionCenter != null) {
            adoptionCenter.getClass(cleansingCenter);
        }
    }


    protected void registerDog(String[] commandList) {
        Animal dog = new Dog(commandList[1], Integer.parseInt(commandList[2])
                , Integer.parseInt(commandList[3]));

        String adoptionCenterName = commandList[4];

        AdoptionCenter adoptionCenter = adoptionCenters.get(adoptionCenterName);
        adoptionCenter.addAnimal(dog);
    }

    private void registerCat(String[] commandList) {
        Animal cat = new Cat(commandList[1], Integer.parseInt(commandList[2])
                , Integer.parseInt(commandList[3]));

        String adoptionCenterName = commandList[4];

        AdoptionCenter adoptionCenter = adoptionCenters.get(adoptionCenterName);
        adoptionCenter.addAnimal(cat);
    }


    protected void stop() {
        this.isRunning = false;
    }

    protected void invalidCommand() {
        userInterface.invalidCommand();
    }

    protected void registerAnimal(String command) {
        String[] animalCreator = commandSplitter(command);
        String name = animalCreator[1];
        int age = Integer.parseInt(animalCreator[2]);
        if (animalCreator[0].equals("RegisterDog")) {
            int learnedCommands = Integer.parseInt(animalCreator[3]);
        } else if (animalCreator[0].equals("RegisterCat")) {
            int intelligenceCoefficient = Integer.parseInt(animalCreator[3]);

        }

    }


    protected String[] commandSplitter(String command) {
        String[] parts = command.split("\\| ");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    protected void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Animal name: ").append(animal.getName()).append(SEPARATOR)
                .append("Age: ").append(animal.getAge()).append(SEPARATOR)
                .append("Skill: ").append(animal.getSkill()).append(SEPARATOR)
                .append("Camps: ").append(animal.getCampList().size());
        if (!animal.getCampList().isEmpty()) {
            for (Camp camp : animal.getCampList()) {
                sb.append(camp);
            }
        }
        this.userInterface.printMessage(sb.toString());
    }


}

