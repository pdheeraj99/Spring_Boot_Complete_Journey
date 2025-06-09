package com.aop.basic_aop_example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.basic_aop_example.model.Alien;
import com.aop.basic_aop_example.repo.IAlienRepo;

@Service
public class AlienService implements IAlienService {

    @Autowired
    private IAlienRepo repo;

    @Override
    public Alien registerAlien(Alien alien) {

        System.out.println("Alien Registered");
        return alien;
        // return repo.save(alien);

    }

    @Override
    public List<Alien> getAllAliensInfo() {

        return repo.findAll();

    }

}
