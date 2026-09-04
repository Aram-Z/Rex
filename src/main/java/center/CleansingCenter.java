package center;

import animal.Animal;

import java.util.HashMap;
import java.util.Map;

public class CleansingCenter {

    private HashMap<Animal, AdoptionCenter> animals;

    /**
     * Létrehoz egy tisztító központot, amely kezdetben üres állatlistával rendelkezik.
     */

    public CleansingCenter() {
        this.animals = new HashMap<>();


    }

    /**
     * Hozzáad egy állatot a tisztítandó állatokhoz, és megjegyzi, hogy melyik örökbefogadó központból érkezett.
     *
     * @param animal a tisztítandó állat
     * @param adoptionCenter az a központ, ahonnan az állat érkezett
     */

    public void addAnimal(Animal animal, AdoptionCenter adoptionCenter) {
        animals.put(animal, adoptionCenter);
    }

    /**
     * Minden még nem tisztított állatot megtisztít, majd visszaküldi őket a megfelelő örökbefogadó központba.
     */

    public void cleanse() {
        if (!animals.isEmpty()) {
            for (Animal animal : animals.keySet()) {
                animal.cleansed();
            }
        }
        resendAnimals();


    }

    /**
     * Visszaküldi az összes megtisztított állatot a hozzájuk tartozó örökbefogadó központba,
     * majd kiüríti a tisztítandó lista tartalmát.
     */

    public void resendAnimals() {
        for (Map.Entry<Animal, AdoptionCenter> entry : animals.entrySet()) {
            Animal animal = entry.getKey();
            AdoptionCenter adoptionCenter = entry.getValue();

            adoptionCenter.addAnimal(animal);
        }

        animals.clear();
    }

    /**
     * Megszámolja, hogy hány olyan állat van a tisztítandó listában,
     * amely még nincs megtisztítva.
     *
     * @return a még tisztítatlan állatok száma
     */

    public int animalsAwaitingClensed(){
        int waitingAnimal = 0;
        if(animals.size() != 0){
            for (Map.Entry<Animal, AdoptionCenter> entry : animals.entrySet()) {
                Animal animal = entry.getKey();
                if(animal.isCleansed() == false){
                    waitingAnimal++;
                }
            }
        }
        return waitingAnimal;
    }
}

