package ru.lesson.Clinic;

import java.util.*;

public class Clinic {
    private String titleName; // Name of Clinic
    private int countClients, countAnimals; // variables for count numbers of Clients and Animals
    private ArrayList<Client> listClient; // list of client
    private int clientIndex; // variable used for unique index  during creation object of Client ( Client.id)

    private final Map<String, Action> operations = new HashMap<String, Action>();

    public void load(Action action) {
        this.operations.put(action.operation(), action);
    }


    /*Constructor of Clinic*/
    public Clinic(String titleName) {
        this.titleName = titleName;
        this.listClient = new ArrayList<Client>();
        this.clientIndex = 0;

        this.load(new showAllClients());
        this.load(new showAllAnimals());
        this.load(new addNewClient());
        this.load(new addNewAnimal());
        this.load(new deleteClient());
        this.load(new deleteAnimal());
        this.load(new findClient());
        this.load(new findAnimal());
        this.load(new editClient());
        this.load(new editAnimal());
        this.load(new showInfo());
        this.load(new showCommands());

    }

    public void doAction(String command, Clinic clinic, Input input, Output output) {
        operations.get(command).execute(clinic, input, output);
    }
 /*LIST OF COMMANDS
    * 1 get all clients
    * 2 get all Animals
    * 3 add Client
    * 4 delete Client
    * 5 add Animal
    * 6 delete Animal
    * 7 find Client
    * 8 find Animal
    * 9 edit Client
    * 10 edit Animal
    * 11 show Info
    * */

    /*Class with start coomand, store a list of available commands */
    public static class showCommands implements Action {

        @Override
        public String operation() {
            return "0";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            output.Println("Please insert the command: \n"
                    + "1 - show all clients\n"
                    + "2 - show all Animals\n"
                    + "3 - add Client\n"
                    + "4 - remove Client\n"
                    + "5 - add Animal\n"
                    + "6 - remove Animal\n"
                    + "7 - find Client\n"
                    + "8 - find Animal\n"
                    + "9 - edit Client\n"
                    + "10 - edit Animal\n"
                    + "11 - show Info\n");
        }
    }

    /*Class-command to show all clinic`s client*/
    public static class showAllClients implements Action {

        @Override
        public String operation() {
            return "1";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            if (clinic.getCountClients() > 0)
                output.PrintList("Clinins contains next сlients:", clinic.getAllCients());
            else
                output.Println("There are no clients in the clinic");
        }
    }

    /*Class-command to show all clinic`s animals*/
    public static class showAllAnimals implements Action {

        @Override
        public String operation() {
            return "2";
        }

        public void execute(Clinic clinic, Input input, Output output) {
            if (clinic.getCountAnimals() > 0)
                output.PrintList("Clinic contains next animals: ", clinic.getAllAnimalNames());
            else
                output.Println("There are no animals in the clinic");
        }
    }

    /*Class-command for add new client*/
    public static class addNewClient implements Action {
        private final static int MIN_AGE = 0;
        private final static int MAX_AGE = 100;

        @Override
        public String operation() {
            return "3";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            String name = input.ask("Input name of new client:");
            int age = input.getValidNumber("Input age of client:", MIN_AGE, MAX_AGE);
            clinic.addClient(name, age);
        }
    }

    /*Class-command for delete exist clinic`s client*/
    public static class deleteClient implements Action {
        private final static int MIN_INDEX = 0;
        private final static int CORRECT_SIZE = 1;

        @Override
        public String operation() {
            return "4";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            if (clinic.getCountClients() > 0) {
                output.PrintList("Clinis contains next clients: ", clinic.getAllCients());
                int index = input.getValidNumber("Input number of client for delete:", MIN_INDEX, clinic.getCountClients() - CORRECT_SIZE);
                output.Println(clinic.removeClient(index).toString() + " has been deleted");
            } else
                output.Println("There are no clients in the clinic");
        }
    }

    /*Class-command for add new animal to clinic*/
    public static class addNewAnimal implements Action {
        private final static int MIN_INDEX = 0;
        private final static int CORRECT_SIZE = 1;
        private final static int TYPE_DOG = 1;
        private final static int TYPE_CAT = 2;

        @Override
        public String operation() {
            return "5";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            if (clinic.getCountClients() > 0) {
                output.PrintList("Clinis contains next clients: ", clinic.getAllCients());
                int index = input.getValidNumber("Input the number of client:", MIN_INDEX, clinic.getCountClients() - CORRECT_SIZE);
                Client clientOwner = clinic.getAllCients().get(index);
                int animalType = input.getValidNumber("Input type of Pet: 1 - DOG; 2 - CAT", TYPE_DOG, TYPE_CAT);
                String animalName = input.ask("Input name of animal:");
                clinic.addAnimal(createAnimal(animalType, animalName), clientOwner);
            } else
                output.Println("There are no clients in the clinic. Add animal is impossible!");
        }

        private Pet createAnimal(int animalType, String animalName) {
            Pet pet;

            if (animalType == TYPE_DOG)
                pet = new Dog(animalName);
            else
                pet = new Cat(animalName);
            return pet;
        }
    }

    /*Class-command for delete exist animal*/
    public static class deleteAnimal implements Action {
        private final static int MIN_INDEX = 0;
        private final static int CORRECT_SIZE = 1;

        @Override
        public String operation() {
            return "6";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            if (clinic.getCountClients() > 0) {
                output.PrintList("Animals owner: ", clinic.getAllCients());
                int indexClient = input.getValidNumber("Input number of animal`s owner: ", MIN_INDEX, clinic.getCountClients() - CORRECT_SIZE);
                Client client = clinic.getAllCients().get(indexClient);
                if (client.getListAnimal().size() > 0) {
                    output.PrintList("Client " + client.getClientName() + " has next animals:", client.getListAnimal());
                    int indexAnimal = input.getValidNumber("Input number of animal for delete: ", MIN_INDEX, client.getListAnimal().size() - CORRECT_SIZE);
                    output.Println(client.removeAnimal(indexAnimal).getName() + "  has been deleted");
                } else
                    output.Println("There are no animals for deleting");
            } else
                output.Println("There are no clients and animals in the clinic");
        }
    }

    /*Class-command for search a client by name*/
    public static class findClient implements Action {

        @Override
        public String operation() {
            return "7";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            String clientName = input.ask("Input name of client for search:");
            output.PrintList("The result of search: ", clinic.findAllClient(clientName));
        }
    }

    /*Class-command for search an animal by name*/
    public static class findAnimal implements Action {
        @Override
        public String operation() {
            return "8";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            String animalName = input.ask("Input the name of animal for search");
            output.PrintList("The result of search", clinic.findAnimalByName(animalName));
        }
    }

    /*Class-command for edit an exist client*/
    public static class editClient implements Action {
        private final static int MIN_INDEX = 0;
        private final static int CORRECT_SIZE = 1;
        private final static int MIN_AGE = 0;
        private final static int MAX_AGE = 100;

        @Override
        public String operation() {
            return "9";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {

            if (clinic.getCountClients() > 0) {
                output.PrintList("Clinic has next clients: ", clinic.getAllCients());
                int index = input.getValidNumber("Input a number of exist client:", MIN_INDEX, clinic.getCountClients() - CORRECT_SIZE);
                Client client = clinic.getAllCients().get(index);
                String newName = input.ask("Input new name for client " + client.getClientName() + " :");
                int newAge = input.getValidNumber("Input new age for client " + client.getClientName() + " :", MIN_AGE, MAX_AGE);
                client.setClientName(newName);
                client.setClientAge(newAge);
                output.Println("The client was successfuly edited");
            } else
                output.Println("There are no clients in the clinic");
        }
    }

    /*Class-command for edit an exist animal*/
    public static class editAnimal implements Action {
        private final static int MIN_INDEX = 0;
        private final static int CORRECT_SIZE = 1;

        @Override
        public String operation() {
            return "10";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {

            if (clinic.getCountClients() > 0) {
                output.PrintList("Animal`s owners:", clinic.getAllCients());
                Pet pet = this.getAnimal(clinic, input, output);
                if (pet != null) {
                    String newName = input.ask("Input new name for animal: ");
                    pet.setName(newName);
                    output.Println("The animal was successfuly edited");
                } else
                    output.Println("There are no animals for edit");
            } else
                output.Println("There are no clients and animals in the clinic");
        }

        private Pet getAnimal(Clinic clinic, Input input, Output output) {
            int indexClient = input.getValidNumber("Input number of animal`s owner: ", MIN_INDEX, clinic.getCountClients() - CORRECT_SIZE);
            Client client = clinic.getAllCients().get(indexClient);
            Pet pet = null;
            if (client.getListAnimal().size() > 0) {
                output.PrintList("Client " + client.getClientName() + " has next animals:", client.getListAnimal());
                int indexAnimal = input.getValidNumber("Input number of animal for edit: ", MIN_INDEX, client.getListAnimal().size() - CORRECT_SIZE);
                pet = client.getListAnimal().get(indexAnimal);
            }
            return pet;
        }
    }
    /*Class-command for show information about clinic*/
    public static class showInfo implements Action {

        @Override
        public String operation() {
            return "11";
        }

        @Override
        public void execute(Clinic clinic, Input input, Output output) {
            output.Println("Clinic " + clinic.getTitleName() + " contains:");
            output.Println("Clients - " + clinic.getCountClients());
            output.Println("Animals - " + clinic.getCountAnimals());
        }
    }


    //--------------------------------------------------------------------------------------------------
    /*Standard GET method*/
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
            if (client.getClientName()==clientName) {
                clientFind = client;
                break;
            }
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

    /*Rturn the list if Strigs with owner and pets*/
    public ArrayList<String> findAnimalByName(String animalName) {
        ArrayList<String> listNames = new ArrayList<String>();
        for (Client client : this.listClient) {
            for (Pet pet : client.getListAnimal())
                if (pet.getName().equals(animalName))
                    listNames.add(pet.toString() + " Owner: " + client.getClientName());
        }
        return listNames;
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

    /*Return a list of String with all Animals in the clinic with their owners*/
    public ArrayList<String> getAllAnimalNames() {
        ArrayList<String> listNames = new ArrayList<String>();
        for (Client client : this.listClient) {
            for (Pet pet : client.getListAnimal())
                listNames.add(pet.toString() + " Owner: " + client.getClientName());
        }
        return listNames;
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
