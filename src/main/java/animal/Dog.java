package animal;

public class Dog extends Animal{
    private int learnCommands;

    public Dog(String name, int age, int learnCommands) {
        super(name, age);
        this.learnCommands
                = learnCommands;
    }

    public int getLearnCommands() {
        return learnCommands;
    }

    public void setLearnCommands(int learnCommands) {
        this.learnCommands = learnCommands;
    }
}
