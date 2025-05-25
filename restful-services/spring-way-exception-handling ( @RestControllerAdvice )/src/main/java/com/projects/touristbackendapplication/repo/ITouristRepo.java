package com.projects.touristbackendapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.touristbackendapplication.model.Tourist;

@Repository
public interface ITouristRepo extends JpaRepository<Tourist, Integer> {

}
