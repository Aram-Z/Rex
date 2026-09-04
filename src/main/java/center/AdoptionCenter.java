package center;

import animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class AdoptionCenter {

    private String name;
    private List<Animal> animals;
    private int adoptedAnimals;

    public AdoptionCenter(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void sendForCleansing(CleansingCenter cleansingCenter) {
       if(animals.size() != 0){
           for (int i = 0; i < animals.size(); i++) {
               if(animals.get(i).isCleansed() == false) {
                   cleansingCenter.addAnimal(animals.get(i), this);
               }
           }
       }
       animals.removeIf(animal -> animal.isCleansed() == false);
    }

    private int adoptigAnimal() {
        List<Animal> clinsedanimallist =new ArrayList<>();
        int adoptedCount = 0;
        if(animals.size() != 0) {
            for (int i = 0; i < animals.size() ; i++) {
                if(animals.get(i).isCleansed() == true) {
                    clinsedanimallist.add(animals.get(i));
                }
            }
            adoptedCount = clinsedanimallist.size();
            animals.removeIf(clinsedanimallist::contains);


        }
        return adoptedCount;

    }

    public String getName() {
        return name;
    }


}