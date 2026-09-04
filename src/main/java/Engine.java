import animal.Animal;
import animal.Cat;
import animal.Dog;
import center.AdoptionCenter;
import center.CleansingCenter;
import userInterface.UserInterface;


import java.util.HashMap;
import java.util.Map;



public class Engine {
    private boolean isRunning;
    private final UserInterface userInterface;
    private Map<String, AdoptionCenter> adoptionCenters = new HashMap<>();
    private CleansingCenter cleansingCenter= new CleansingCenter();
    private int adoptedAnimals = 0;
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
                adoptionCenters.get(commandList[1]).sendForCleansing(cleansingCenter);
                break;

            case "Cleanse":
                cleansingCenter.cleanse();
                break;

            case "Adopt":
                adoptedAnimals += adoptionCenters.get(commandList[1]).adoptigAnimal();
                break;

            case "Quit":
                stop();
                printStatus();
                break;

            default:
                invalidCommand();
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

    protected String[] commandSplitter(String command) {
        String[] parts = command.split("\\| ");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    public int allAdaptionsWitingsAnimals() {
        int waitingAnimals = 0;
        if(adoptionCenters.size() != 0){
            for (Map.Entry<String, AdoptionCenter> entry : adoptionCenters.entrySet()) {
                AdoptionCenter adoptionCenter = entry.getValue();
                waitingAnimals += adoptionCenter.animalsAwaitingAdoption();
            }
        }
        return waitingAnimals;
    }

    protected void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rex Incorporated Regular Statistics").append(SEPARATOR)
                .append("Adoption Centers: ").append(adoptionCenters.size()).append(SEPARATOR)
                .append("Adopted Animals: ").append(adoptedAnimals).append(SEPARATOR)
                .append("Animals Awaiting Adoption: ").append(allAdaptionsWitingsAnimals()).append(SEPARATOR)
                .append("Animals Awaiting Cleansing: ").append(cleansingCenter.animalsAwaitingClensed());



        this.userInterface.printMessage(sb.toString());
    }


}

