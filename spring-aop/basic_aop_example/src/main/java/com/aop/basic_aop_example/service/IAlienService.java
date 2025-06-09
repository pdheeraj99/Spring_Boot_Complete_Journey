package com.aop.basic_aop_example.service;

import java.util.List;

import com.aop.basic_aop_example.model.Alien;

public interface IAlienService {

    Alien registerAlien(Alien alien);

    List<Alien> getAllAliensInfo();

}
