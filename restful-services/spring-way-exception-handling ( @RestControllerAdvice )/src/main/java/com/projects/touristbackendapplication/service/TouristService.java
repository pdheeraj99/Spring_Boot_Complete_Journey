package com.projects.touristbackendapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.touristbackendapplication.custom_exceptions.TouristNotFoundException;
import com.projects.touristbackendapplication.model.Tourist;
import com.projects.touristbackendapplication.repo.ITouristRepo;

@Service
public class TouristService implements ITouristService {

    @Autowired
    private ITouristRepo repo;

    @Override
    public String registerTourist(Tourist tourist) {

        Tourist saveTourist = repo.save(tourist);

        return "Tourist Info registered with ID:::" + saveTourist.getId();
    }

    @Override
    public Tourist fetchTouristById(Integer id) {
        // ====> Method 1
        // Optional<Tourist> tourist = repo.findById(id);
        // if (tourist.isPresent()) {
        // return tourist.get();
        // } else {
        // throw new TouristNotFoundException("Tourist with id not found");
        // }

        // ===> Method 2
        return repo.findById(id).orElseThrow(() -> new TouristNotFoundException("Tourist with id not there man"));
    }

    @Override
    public List<Tourist> fetchAllTouristInfo() {
        return repo.findAll();
    }

    @Override
    public String updateTouristInfo(Tourist tourist) {
        Optional<Tourist> optional = repo.findById(tourist.getId());
        if (optional.isPresent()) {
            repo.save(tourist);
            return "Tourist info updated success";
        }
        throw new TouristNotFoundException("Tourist with id not found");
    }

    @Override
    public String updateTheTouristBudget(Integer id, Double budget) {
        Optional<Tourist> optional = repo.findById(id);
        if (optional.isPresent()) {
            Tourist t = optional.get();
            t.setBudget(budget);
            repo.save(t);
            return "Tourist info updated for budget";
        }
        throw new TouristNotFoundException("Tourist with id not found");
    }

    @Override
    public String deleteTouristInfoById(Integer id) {
        Optional<Tourist> optional = repo.findById(id);
        if (optional.isPresent()) {

            repo.deleteById(id);
            return "Tourist record for the return id deleted";
        }
        throw new TouristNotFoundException("Tourist with id not found");
    }

}
