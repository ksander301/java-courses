import java.util.ArrayList;

public class Client {
    private String clientName; /* Name of client*/
    private int clientAge;/* Clein`s Age*/
    private ArrayList<Pet> listAnimal; /* Clinet`s list of Pets, used interface TYPE for saving object DOG & CAT */
    private int id;/* Unique ID of client*/

    /**
     * Constructor with Name, AGE,ID
     */
    public Client(String clientName, int clientAge, int id) {
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.listAnimal = new ArrayList<Pet>();
        this.id = id;
    }

    /**
     * Constructor with Name, ID, (not used in current version)
     */
    public Client(String clientName, int id) {
        this.clientName = clientName;
        this.id = id;
        this.listAnimal = new ArrayList<Pet>();

    }

    /* Stantard GET method*/
    public String getClientName() {
        return clientName;
    }

    /* Stantard SET method*/
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /* Stantard GET method*/
    public int getClientAge() {
        return clientAge;
    }

    /*Stantard SET method*/
    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }

    /* Stantard GET method*/
    public ArrayList<Pet> getListAnimal() {
        return listAnimal;
    }

    /* Stantard SET method*/
    public void setListAnimal(ArrayList<Pet> listAnimal) {
        this.listAnimal = listAnimal;
    }

    /**
     * Return the Pet which name match of search animal`s name
     */
    public Pet findAnimal(String animalName) {
        Pet animalFinded = null;
        for (Pet animal : listAnimal) {
            if (animal.getName().equals((animalName)))
                animalFinded = animal;
        }
        return animalFinded;
    }

    /* Add animal to client`s list of animals */
    public void addAnimal(Pet animal) {
        listAnimal.add(animal);
    }

    /**
     * Return result of remove animal from list of animals
     */
    public boolean removeAnimal(Pet animalToRemove) {
        return listAnimal.remove(animalToRemove);
    }

    /**
     * Remove animal from list by index
     */
    public Pet removeAnimal(int index) {
        return listAnimal.remove(index);
    }

    /**
     * Get unique ID of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Return the result of match with any other client, use match by Name and ID
     */
    public boolean equals(Client client) {
        boolean isEqual = false;
        if (this.id == client.getId())
            isEqual = true;
        return isEqual;
    }

    @Override
    public String toString() {
        return "Client: " + this.clientName + " Age: " + this.clientAge;
    }
}
