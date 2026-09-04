package animal;

public abstract class Animal {

    private String name;
    private int age;
    private  boolean cleansed;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.cleansed = false;
    }



    public String getName() {
        return name;
    }



    public int getAge() {
        return age;
    }



    public boolean isCleansed() {
        return cleansed;
    }



    public void cleansed(){
        this.cleansed = true;
    }
}
