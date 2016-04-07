package clinic.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClinicRunner {

    private Scanner scanner;
    private Clinic clinic;
    private String command; // handling user input command

    /* Constants for type op animal*/
    private final static int DOG_TYPE = 1;
    private final static int CAT_TYPE = 2;

    public static void main(String[] args) throws IOException {
        ClinicRunner clinicRunner = new ClinicRunner();
        clinicRunner.startClinic();
    }

    /*Constructor ClinicRunner*/
    public ClinicRunner() {
        scanner = new Scanner(System.in);
        clinic = new Clinic("Clinic of Pets");
    }

    /*Start a proccess of dialog with user*/
    public void startClinic() throws IOException {
        this.getCommand();
    }

    /*LIST OF COMMANDS
    * 1 get all clients
    * 2 get all Animals
    * 3 add Client
    * 4 remove Client
    * 5 add Animal
    * 6 remove Animal
    * 7 find Client
    * 8 find Animal
    * 9 edit Client
    * 10 edit Animal
    * 11 show Info
    * */

    /* Offer to input one of availible command*/
    private void getCommand() throws IOException {
        System.out.println("Please insert the command: \n"
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
        this.command = scanner.nextLine();
        if (this.validateCommand(command))
            this.executeCommand(command);
        else this.getCommand();
    }

    /* Select and execute one of operation  by the user input coommand*/
    private void executeCommand(String command) throws IOException {
        switch (command) {
            case "1":
                this.showAllClients(this.clinic.getAllCients());
                break;
            case "2":
                this.showAllAnimals(this.clinic.getAllCients());
                break;
            case "3":
                this.addNewClient();
                break;
            case "4":
                this.deleteClient();
                break;
            case "5":
                this.addNewAnimal();
                break;
            case "6":
                this.deleteAnimal();
                break;
            case "7":
                this.findClient();
                break;
            case "8":
                this.findAnimal();
                break;
            case "9":
                this.editClient();
                break;
            case "10":
                this.editAnimal();
                break;
            case "11":
                this.showInfo();
                break;
        }
        this.getExitCommand();
    }

    /*Show count of clients and animals in the clinic */
    public void showInfo() {
        System.out.println("Clinic " + this.clinic.getTitleName() + " contains:");
        System.out.println("Clients - " + this.clinic.getCountClients());
        System.out.println("Animals - " + this.clinic.getCountAnimals());

    }

    /*Offer to select and change (name,age) one of client in the clinic*/
    public void editClient() throws IOException {
        if (this.clinic.getCountClients() != 0) {
            Client client = this.chooseClient();
            System.out.println("Input new name for client -" + client.getClientName());
            String clientName = scanner.nextLine();
            System.out.println("Input new age for client -" + client.getClientName());
            client.setClientName(clientName);
            int clientAge = this.getValidAge();
            client.setClientAge(clientAge);
            System.out.println("The client has been successfuly edited ");
        } else
            System.out.println("There are  no clients for edit!");
    }

    /*Offer to select  one of client in the and change Name  for one of his Pets*/
    public void editAnimal() {
        if (this.clinic.getCountClients() != 0) {
            this.showAllAnimals(this.clinic.getAllCients());
            System.out.println("Choose  animal`s owner:");
            Client clientOwner = this.chooseClient();
            System.out.println("Choose  animal for edit:");
            Pet pet = this.chooseAnimal(clientOwner);
            System.out.print("Input new name for animal:");
            String animalName = scanner.nextLine();
            pet.setName(animalName);
            System.out.println("The animal has been successfuly edited ");
        } else
            System.out.println("There are no animals for edit!");
    }

    /*Find all clients which name is matched for input string*/
    public void findClient() {
        System.out.println("Input client`s name for search: ");
        String clientName = scanner.nextLine();
        ArrayList<Client> listClients = this.clinic.findAllClient(clientName);
        if (listClients.size() > 0)
            showAllClients(listClients);
        else
            System.out.println("No one client was fined...");
    }

    /*Find all animals and their owners which name is matched for input string*/
    public void findAnimal() {
        System.out.println("Input animal`s name for search: ");
        String animalName = scanner.nextLine();
        ArrayList<Client> listClientAnimals = this.clinic.findAllAnimalByName(animalName);
        if (listClientAnimals.size() > 0)
            showAllAnimals(listClientAnimals);
        else
            System.out.println("No one animals was fined...");
    }

    /*Offer to choose animal`s owner and delete one of his animal*/
    public void deleteAnimal() {
        this.showAllAnimals(this.clinic.getAllCients());
        System.out.println("Input owner Number to remove animal:");
        Client clientOwner = this.chooseClient();
        Pet petToRemove = this.chooseAnimal(clientOwner);
        if (clientOwner.removeAnimal(petToRemove))
            System.out.println("DELETED: " + petToRemove.toString());
        else
            System.out.println("An animal has not been deleted!");
    }

    /*Offer to remove one of clients*/
    public void deleteClient() {
        int clientNumber;
        System.out.println("Input the number of client to remove:");
        Client clientToRemove = this.chooseClient();
        if (this.clinic.removeClient(clientToRemove))
            System.out.println("DELETED: " + clientToRemove.toString());
        else
            System.out.println("A client has not been deleted!");
    }

    /* Offer to input NAME and AGE of new client, than save him*/
    private void addNewClient() throws IOException {
        System.out.println("Input name of Client:");
        String name = this.scanner.nextLine();
        int age = this.getValidAge();
        this.clinic.addClient(name, age);

    }

    /* Add new animal to one of clients in the clinic*/
    private void addNewAnimal() throws IOException {
        Client clientAnimal;
        int clientNumber;
        Pet newAnimal;
        if (this.clinic.getCountClients() == 0)
            this.addNewClient();
        System.out.println("Input the number of exist client:");
        clientAnimal = chooseClient();
        newAnimal = this.createAnimal();
        this.clinic.addAnimal(newAnimal, clientAnimal);
    }

    /* Offer to input NAME and select type of new animal, return  PET*/
    public Pet createAnimal() {
        System.out.println("Input name of Animal");
        String animalName = this.scanner.nextLine();
        Pet pet;
        System.out.println("Input the type of Animal: 1 - DOG , 2 - CAT");
        if (this.getValidAnimalType() == DOG_TYPE)
            pet = new Dog(animalName);
        else
            pet = new Cat(animalName);
        return pet;
    }

    /* Check that user input number is was beetwen 0 and maxNumber, support method for work with lists*/
    public int getValidNumber(int maxNumber) {
        int validNumber = -1;
        boolean success = false;
        while (!success) {
            try {
                validNumber = Integer.decode(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Incorrect number format. Error. +" + e.toString());
            }
            if (validNumber >= 0 && validNumber < maxNumber)
                success = true;
            else
                System.out.println("Number is not exist. Try again");
        }
        return validNumber;
    }

    /* Support metthod, check that user input (type of animal) is was 1 or 2, used in method createAnimal  */
    public int getValidAnimalType() {
        boolean success = false;
        int petType = -1;
        while (!success) {
            try {
                petType = Integer.decode(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid number formar.Error: " + e.toString());
            }
            if (petType == DOG_TYPE || petType == CAT_TYPE)
                success = true;
            else
                System.out.println("Incorrect type of Animal. Try again...");
        }
        return petType;
    }
    /* Check that user input was a naumber, used in method addNewClient*/
    public int getValidAge() throws IOException {
        int age = -1;
        boolean success = false;
        while (!success)
            try {
                System.out.println("Insert clients age:");
                age = Integer.decode(scanner.nextLine());
                success = true;
            } catch (Exception e) {
                System.out.println("Incorrect number format. Error. +" + e.toString());
            }
        return age;
    }

    /*Show the list of Animals with their owners*/
    public void showAllAnimals(ArrayList<Client> listClients) {
        Client client;
        for (int i = 0; i < listClients.size(); i++) {
            client = listClients.get(i);
            for (Pet pet : client.getListAnimal())
                System.out.println(" Owner:(№ " + i + ") " + client.getClientName() + " Animal: " + pet.getName() + " Type: " + pet.getType());
        }
        System.out.println();
    }
    /*Show the list of clients, used in different methods */
    public void showAllClients(ArrayList<Client> listClients) {
        Client client;
        for (int i = 0; i < listClients.size(); i++) {
            client = listClients.get(i);
            System.out.println("№: " + i + " Client: " + client.getClientName() + " Age: " + client.getClientAge());
        }
        System.out.println();
    }

    /*Validate the input command, return true where coomand is valid*/
    private boolean validateCommand(String command) {
        boolean isValid = false;
        if (command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") ||
                command.equals("6") || command.equals("7") || command.equals("8") || command.equals("9") || command.equals("10") || command.equals("11"))
            isValid = true;
        return isValid;
    }

    /*Offer to input yes/no exit command, with YES option run method getCommand() */
    public void getExitCommand() throws IOException {
        System.out.println("Exit from " + clinic.getTitleName() + "? [yes/no]:");
        this.command = scanner.nextLine();
        if (!validateExit(command))
            this.getExitCommand();
        else if (command.equals("yes"))
            this.exitClinicRunner();
        else
            this.getCommand();
    }
    /*Validate the user input command for yes/no*/
    private boolean validateExit(String command) {
        boolean isValid = false;
        if (command.equals("yes") || command.equals("no"))
            isValid = true;
        return isValid;
    }

    /*Offer to select one of exists clients, support method, used in different methods */
    private Client chooseClient() {
        ArrayList<Client> listClient = this.clinic.getAllCients();
        Client client = null;
        this.showAllClients(this.clinic.getAllCients());
        if (listClient.size() > 0) {
            int clientNumber = this.getValidNumber(listClient.size());
            client = listClient.get(clientNumber);
        }
        return client;
    }
    /*Offer to select one of animals for selected client */
    private Pet chooseAnimal(Client client) {
        Pet resultPet = null;
        ArrayList<Pet> listAnimal = client.getListAnimal();
        if (listAnimal.size() > 0) {
            for (int i = 0; i < listAnimal.size(); i++)
                System.out.println("№: " + i + " " + listAnimal.get(i).toString());
            int animalNumber = this.getValidNumber(listAnimal.size());
            resultPet = listAnimal.get(animalNumber);
        }
        return resultPet;
    }

    /*not used, show the list of object, may be useful fo list of objects with overrided method toString()*/
    public void showList(ArrayList listObjects) {
        for (int i = 0; i < listObjects.size(); i++) {
            System.out.println("№: " + i + " " + listObjects.get(i).toString());
        }
    }

    /* close the input stream*/
    private void exitClinicRunner() {
        scanner.close();
    }
}
