package com.projects.touristbackendapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.touristbackendapplication.model.Tourist;
import com.projects.touristbackendapplication.service.ITouristService;

@RestController
public class TouristController {

    private ITouristService service;

    @Autowired
    public void setService(ITouristService service) {
        this.service = service;
    }

    @PostMapping("/reg-tourist")
    public ResponseEntity<String> registerTourist(@RequestBody Tourist tourist) {
        String res = service.registerTourist(tourist);
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get-tourist/{id}")
    public ResponseEntity<?> getTourist(@PathVariable("id") Integer id) {
        Tourist tourist = service.fetchTouristById(id);
        return new ResponseEntity<Tourist>(tourist, HttpStatus.OK);
    }

    @PutMapping("/update-tourist")
    public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist) {
        String res = service.updateTouristInfo(tourist);
        return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update-tourist/{id}/{budget1}")
    public ResponseEntity<String> updateTouristByIdWithBudget(@PathVariable("id") Integer id,
            @PathVariable("budget1") Double budget) {
        String res = service.updateTheTouristBudget(id, budget);
        return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/update-tourist/{id}")
    public ResponseEntity<String> deleteTouristById(@PathVariable("id") Integer id) {
        String res = service.deleteTouristInfoById(id);
        return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-tourists")
    public ResponseEntity<List<Tourist>> getAllTourists() {
        List<Tourist> touristList = service.fetchAllTouristInfo();
        return new ResponseEntity<List<Tourist>>(touristList, HttpStatus.OK);
    }

}
