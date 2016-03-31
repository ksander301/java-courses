
public class Cat implements Pet {
    private String animalName;
    private final String animalType;

    /**
     * Constructor of class CAT with define animalType
     */
    public Cat(String animalName, String animalType) {
        this.animalName = animalName;
        this.animalType = animalType;
    }

    /*
    * Construct of class DOG, animalType by default
    */
    public Cat(String animalName) {
        this.animalName = animalName;
        this.animalType = "Cat";
    }

    /**
     * implementation method getName of interface Pet
     */
    public String getName() {
        return this.animalName;
    }

    /**
     * implementation method setName of interface Pet
     */
    public void setName(final String animalName) {
        this.animalName = animalName;
    }

    /**
     * implementation method getType of interface Pet
     */
    public String getType() {
        return this.animalType;
    }

    /**
     * Override the method toString of class Object
     */
    public String toString() {
        return "Animal: " + animalName + " Type: " + animalType;
    }
}
