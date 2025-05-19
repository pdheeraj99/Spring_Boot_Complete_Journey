package com.learn_jacksonapi;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn_jacksonapi.model.Alien;

public class Java2Json {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        // SCENARIO 1: JAVA OBJECTS TO JSON

        // Create an instance of Alien class
        Alien al = new Alien(1, "Dheeraj", "Earth");
        System.out.println("Alien Object is :" + al);
        String json = mapper.writeValueAsString(al);
        System.out.println("JSON is :" + json);

        // Create a list of Alien objects
        Alien al1 = new Alien(2, "Priya", "Mars");
        Alien al2 = new Alien(3, "Sandhya", "Sun");
        Alien al3 = new Alien(4, "Roopa", "Pluto");
        List<Alien> aliens = Arrays.asList(al1, al2, al3);
        System.out.println("List of Alien Objects are :" + aliens);
        String json1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aliens);
        System.out.println("JSON is :" + json1);

    }
}