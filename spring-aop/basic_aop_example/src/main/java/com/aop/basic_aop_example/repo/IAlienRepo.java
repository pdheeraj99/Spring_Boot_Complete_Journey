package com.aop.basic_aop_example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aop.basic_aop_example.model.Alien;

@Repository
public interface IAlienRepo extends JpaRepository<Alien, Integer> {

}
