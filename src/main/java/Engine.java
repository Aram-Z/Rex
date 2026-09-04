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

    /**
     * Létrehoz egy új futtató motorhoz tartozó kezelőt a megadott felhasználói felülethez.
     *
     * @param userInterface a felhasználói felület, amely a parancsokat kezeli
     */
    public Engine(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.isRunning = true;
    }

    /**
     * Fő ciklus, amely addig dolgozik, amíg a motor fut.
     * Minden lépésben beolvas egy parancsot, feldolgozza, majd folytatja a működést.
     */

    public void run() {
        while (isRunning) {
            process(userInterface.getCommand());
        }
        userInterface.end();
    }

    /**
     * Feldolgozza a kapott parancsot, és a megfelelő műveletet hajtja végre.
     *
     * @param command a feldolgozandó parancs szövege
     */


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

    /**
     * Regisztrál egy új kutyát a megadott örökbefogadó központban.
     *
     * @param commandList a parancs részei: név, életkor, fajta, központ név
     */

    protected void registerDog(String[] commandList) {
        Animal dog = new Dog(commandList[1], Integer.parseInt(commandList[2])
                , Integer.parseInt(commandList[3]));

        String adoptionCenterName = commandList[4];

        AdoptionCenter adoptionCenter = adoptionCenters.get(adoptionCenterName);
        adoptionCenter.addAnimal(dog);
    }

    /**
     * Regisztrál egy új macskát a megadott örökbefogadó központban.
     *
     * @param commandList a parancs részei: név, életkor, fajta, központ név
     */

    private void registerCat(String[] commandList) {
        Animal cat = new Cat(commandList[1], Integer.parseInt(commandList[2])
                , Integer.parseInt(commandList[3]));

        String adoptionCenterName = commandList[4];

        AdoptionCenter adoptionCenter = adoptionCenters.get(adoptionCenterName);
        adoptionCenter.addAnimal(cat);
    }

    /**
     * Leállítja a motor működését.
     */

    protected void stop() {
        this.isRunning = false;
    }

    /**
     * Érvénytelen parancs esetén értesíti a felhasználót.
     */

    protected void invalidCommand() {
        userInterface.invalidCommand();
    }

    /**
     * A parancs szövegét feldarabolja "|" karakterek mentén, majd eltávolítja a felesleges szóközöket.
     *
     * @param command a feldarabolandó parancs
     * @return a parancs részeiből álló tömb
     */

    protected String[] commandSplitter(String command) {
        String[] parts = command.split("\\| ");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    /**
     * Összegzi, hogy az összes örökbefogadó központban hány állat vár örökbefogadásra.
     *
     * @return a befogadásra váró állatok száma
     */

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

    /**
     * Kiírja a rendszer aktuális összegzését, beleértve az örökbefogadó központok számát,
     * az örökbefogadott és befogadásra váró állatok mennyiségét, valamint a tisztításra váró állatokat.
     */

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

