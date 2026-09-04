package center;

import animal.Animal;

import java.util.HashMap;

public class CleansingCenter {

    private HashMap<Animal,AdoptionCenter> animals;

    public CleansingCenter() {
        this.animals = new HashMap<>();


    }
    void addAnimal(Animal animal, AdoptionCenter adoptionCenter) {
        animals.put(animal, adoptionCenter);
    }
}
