package ru.lesson.Clinic;

import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import static org.junit.Assert.*;

public class ClinicTest {

    @Test
    public void testFindClient() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Client client1 = new Client("Vasya", 1);
        final Client client2 = new Client("Vasya", 2);
        final Client client3 = new Client("Petya", 3);
        //action
        clinic.getAllCients().add(client1);
        clinic.getAllCients().add(client2);
        clinic.getAllCients().add(client3);
        //assert
        assertEquals(clinic.findAllClient("Vasya").size(),2);
        assertEquals(client3, clinic.findClient("Petya"));
    }


    @Test
    public void testRemoveAllClient() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        //action
        clinic.addClient("Vasya", 12);
        clinic.addClient("Vasya", 15);
        //assert
        assertTrue(clinic.removeAllClient("Vasya"));
        assertEquals(0, clinic.getCountClients());
    }

    @Test
    public void testRemoveClient() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Client client = new Client("Vasya", 1);
        final Client client2 = new Client("Petya", 2);
        //action
        clinic.getAllCients().add(client);
        clinic.getAllCients().add(client2);
        //assert
        assertEquals(client, clinic.removeClient(0));
        assertTrue(clinic.removeClient(client2));

    }

    @Test
    public void testFindAnimalByName() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Pet dog = new Dog("Pet", "1");
        final Pet cat = new Dog("Pet", "2");
        //action
        clinic.addClient("Vasya", 21);
        clinic.addAnimal(dog, clinic.getAllCients().get(0));
        //assert
        assertNotNull(clinic.findAnimalByName("Pet"));
        assertEquals(clinic.findAnimalByName("Pet").size(), 1);

    }

    @Test
    public void testFindAllAnimalByName() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Pet dog = new Dog("Pet", "1");
        final Pet cat = new Dog("Pet", "2");
        final Client client1 = new Client("Vasya", 1);
        final Client client2 = new Client("Petya", 2);
        //action
        clinic.getAllCients().add(client1);
        clinic.getAllCients().add(client2);
        clinic.addAnimal(dog, client1);
        clinic.addAnimal(cat, client2);
        //assert
        assertNotNull(clinic.findAllAnimalByName("Pet"));
        assertEquals(clinic.findAllAnimalByName("Pet").size(), 2);

    }

    @Test
    public void testGetAllAnimalNames() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Pet dog = new Dog("Bobik", "1");
        final Client client = new Client("Vasya", 1);
        //action
        clinic.getAllCients().add(client);
        clinic.addAnimal(dog, client);
        clinic.addAnimal(dog, client);
        //assert
        assertNotNull(clinic.getAllAnimalNames());
        assertEquals(clinic.getAllAnimalNames().size(), 2);

    }


    @Test
    public void testAddAnimal() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Pet dog = new Dog("Bobik", "1");
        final Pet cat = new Cat("Vasya", "2");
        //action
        clinic.addClient("Petya");
        clinic.addAnimal(dog, clinic.getAllCients().get(0));
        clinic.addAnimal(cat, clinic.getAllCients().get(0));
        //assert
        assertNotNull(clinic.getAllCients());
        assertNotNull(clinic.getAllCients().get(0).getListAnimal());

    }

    @Test
    public void testGetCountClients() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        //action
        clinic.addClient("Petya");
        clinic.addClient("Vova");
        //assert
        assertEquals(2, clinic.getCountClients());
    }

    @Test
    public void testGetCountAnimals() throws Exception {
        //assign
        final Clinic clinic = new Clinic("Any pets");
        final Pet dog = new Dog("Bobik", "1");
        final Pet cat = new Cat("Vasya", "2");
        //action
        clinic.addClient("Petya", 12);
        clinic.addClient("Vova", 22);
        clinic.addAnimal(dog, clinic.getAllCients().get(0));
        clinic.addAnimal(dog, clinic.getAllCients().get(1));
        //assert
        assertEquals(2, clinic.getCountAnimals());
    }
}

