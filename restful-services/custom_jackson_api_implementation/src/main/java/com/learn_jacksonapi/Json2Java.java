package com.learn_jacksonapi;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn_jacksonapi.model.Alien;

public class Json2Java {

    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        ObjectMapper objMap = new ObjectMapper();

        // Reading single object
        Alien alien = objMap.readValue(new File(
                "D:\\Spring_Boot_Complete\\restful-services\\custom_jackson_api_implementation\\json\\sample.json"),
                Alien.class);
        System.out.println(alien);

    }

}
