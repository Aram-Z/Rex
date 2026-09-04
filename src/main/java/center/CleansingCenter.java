package center;

import animal.Animal;

import java.util.HashMap;
import java.util.Map;

public class CleansingCenter {

    private HashMap<Animal, AdoptionCenter> animals;

    public CleansingCenter() {
        this.animals = new HashMap<>();


    }

    void addAnimal(Animal animal, AdoptionCenter adoptionCenter) {
        animals.put(animal, adoptionCenter);
    }

    public void cleanse() {
        if (!animals.isEmpty()) {
            for (Animal animal : animals.keySet()) {
                animal.cleansed();
            }
        }
        resendAnimals();


    }

    public void resendAnimals() {
        for (Map.Entry<Animal, AdoptionCenter> entry : animals.entrySet()) {
            Animal animal = entry.getKey();
            AdoptionCenter adoptionCenter = entry.getValue();

            adoptionCenter.addAnimal(animal);
        }

        animals.clear();
    }
}

