package com.poe.demoBean.business;

import java.util.ArrayList;

public class ListStore implements PersonStoreInterface{

    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person){
        persons.add(person);
    }

    public int size(){
        return persons.size();
    }
}
