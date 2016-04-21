package ru.lesson.Clinic;

public class Dog implements Pet {
    private String animalName;
    private final String animalType;

    /**
     * Constructor of class DOG with define animalType
     */
    public Dog(String animalName, String animalType) {
        this.animalName = animalName;
        this.animalType = animalType;
    }

    /*
    * Construct of class DOG, animalType by default
    */
    public Dog(String animalName) {
        this.animalName = animalName;
        this.animalType = "DOG";
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
