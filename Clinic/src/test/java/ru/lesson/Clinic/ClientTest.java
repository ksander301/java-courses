package ru.lesson.Clinic;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClientTest {


    @Test
    public void testSetListAnimal() {
        //assign
        final Client client = new Client("Vasya", 1);
        final ArrayList<Pet> listAnimal= new ArrayList<Pet>();
        Pet pet1 = new Dog("Tyzik");
        Pet pet2 = new Cat("Barsik");
        //action
        listAnimal.add(pet1);
        listAnimal.add(pet2);
        client.setListAnimal(listAnimal);
        //assert
        assertThat(listAnimal,is(client.getListAnimal()));

    }

    @Test
    public void testFindAnimal() {
        //assign
        final Client client = new Client("Vasya", 1);
        Pet pet1 = new Dog("Tyzik");
        Pet pet2 = new Cat("Barsik");
        //actions
        client.addAnimal(pet1);
        client.addAnimal(pet2);
        //assert
        assertThat(pet1, is(client.findAnimal("Tyzik")));
        assertThat(pet2, is(client.findAnimal("Barsik")));
    }

    @Test
    public void testRemoveAnimal(){
        //assign
        final Client client = new Client("Vasya", 1);
        Pet pet1 = new Dog("Tyzik");
        Pet pet2 = new Cat("Barsik");
        //actions
        client.addAnimal(pet1);
        client.addAnimal(pet2);
        client.removeAnimal(pet1);
        //assert
        assertThat(1,is(client.getListAnimal().size()));
        assertThat(pet2,is(client.getListAnimal().get(0)));
    }
}