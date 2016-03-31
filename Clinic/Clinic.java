import java.util.ArrayList;

public class Clinic {
    private String titleName; // Name of Clinic
    private int countClients, countAnimals; // variables for count numbers of Clients and Animals
    private ArrayList<Client> listClient; // list of client
    private int clientIndex; // variable used for unique index  during creation object of Client ( Client.id)

    /*Constructor of Clinic*/
    public Clinic(String titleName) {
        this.titleName = titleName;
        this.listClient = new ArrayList<Client>();
        this.clientIndex = 0;
    }

    /*Standatd GET method*/
    public String getTitleName() {
        return titleName;
    }

    /*Return NEW list of clients which name is match to search line */
    public ArrayList<Client> findAllClient(String clientName) {
        ArrayList<Client> listFind = new ArrayList<Client>();
        this.listClient.stream().forEach((client -> {
            if (client.getClientName().equals(clientName))
                listFind.add(client);
        }));
        return listFind;
    }

    /*Return the first client which name is match to search line, not used*/
    public Client findClient(String clientName) {
        Client clientFind = null;
        for (Client client : this.listClient) {
            if (client.getClientName().equals(clientName))
                clientFind = client;
            break;
        }
        return clientFind;
    }

    /*Remove all clients from list of clients which name is match to search line*/
    public boolean removeAllClient(String clientName) {
        ArrayList<Client> removeClients;
        removeClients = findAllClient(clientName);
        return listClient.removeAll(removeClients);
    }

    /*Remove client from list of clients*/
    public boolean removeClient(Client clientToRemove) {
        return this.listClient.remove(clientToRemove);
    }
    /*Remove client from list of clients by index*/
    public Client removeClient(int index) {
        return this.listClient.remove(index);
    }

    /*Try to find animal owner by name of animal*/
    public Client findAnimalByName(String animalName) {
        Client clientWthAnimal = null;
        for (Client client : this.listClient)
            for (Pet animal : client.getListAnimal())
                if (animal.getName().equals(animalName)) {
                    clientWthAnimal = client;
                    break;
                }
        return clientWthAnimal;
    }

    /* Return all clients  (list of clients)   that have a PET with name is mathced to search line */
    public ArrayList<Client> findAllAnimalByName(String animalName) {
        ArrayList<Client> clientsWthAnimal = new ArrayList<Client>();
        for (Client client : this.listClient)
            for (Pet animal : client.getListAnimal())
                if (animal.getName().equals(animalName)) {
                    clientsWthAnimal.add(client);
                    break;
                }
        return clientsWthAnimal;
    }

    /* add clinent to clinic`s list only by name, variable CLIENTINDEX define a new client`s ID */
    public void addClient(String clientName) {
        this.listClient.add(new Client(clientName, ++clientIndex));

    }
    /* add clinent to clinic`s list */
    public void addClient(String clientName, int age) {
        this.listClient.add(new Client(clientName, age, ++clientIndex));
    }

    /* Get list of clients*/
    public ArrayList<Client> getAllCients() {
        return this.listClient;
    }

    /* add animal with his owner to Clinic*/
    public void addAnimal(Pet newAnimal, Client clientOwner) {
        for (Client client : this.listClient)
            if (client.equals(clientOwner))
                client.getListAnimal().add(newAnimal);
    }

    /* get count of Clients*/
    public int getCountClients() {
        countClients = this.listClient.size();
        return countClients;
    }
    /* get count of Animals*/
    public int getCountAnimals() {
        this.countAnimals = 0;
        for (int i = 0; i < listClient.size(); i++) {
            countAnimals += listClient.get(i).getListAnimal().size();
        }
        return this.countAnimals;
    }
}
