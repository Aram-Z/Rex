package center;

import animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class AdoptionCenter {

    private String name;
    private List<Animal> animals;
    private int adoptedAnimals;

    /**
     * Létrehoz egy örökbefogadó központot a megadott névvel.
     * A központ kezdetben üres állatlistával rendelkezik.
     *
     * @param name a központ neve
     */

    public AdoptionCenter(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    /**
     * Hozzáad egy új állatot a központ állatlistájához.
     *
     * @param animal a hozzáadandó állat
     */

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    /**
     * Elküldi a még nem tisztított állatokat a tisztító központba,
     * majd eltávolítja őket a központ listájából.
     *
     * @param cleansingCenter az a tisztító központ, amelyhez az állatokat továbbítjuk
     */

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

    /**
     * Örökbefogadásra alkalmas, már megtisztított állatokat kiválasztja,
     * megszámolja őket, és eltávolítja a listából.
     *
     * @return az örökbefogadott állatok száma
     */

    public int adoptigAnimal() {
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

    /**
     * Megszámolja, hogy hány olyan állat van a listában,
     * amely már megtisztítva vár örökbefogadásra.
     *
     * @return a befogadásra váró, megtisztított állatok száma
     */

    public int animalsAwaitingAdoption() {
        int waitingAnimal = 0;
        if(animals.size() != 0){
            for (int i = 0; i < animals.size(); i++) {
                if(animals.get(i).isCleansed() == true){
                    waitingAnimal++;
                }
            }
        }
        return waitingAnimal;
    }

    /**
     * Visszaadja a központ nevét.
     *
     * @return a központ neve
     */
    public String getName() {
        return name;
    }


}