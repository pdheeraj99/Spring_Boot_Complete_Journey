package com.projects.touristbackendapplication.rest;

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

    // ? - represents ResponseEntity can return either Tourist object or String
    // object
    @GetMapping("/get-tourist/{id}")
    public ResponseEntity<?> getTourist(@PathVariable("id") Integer id) {
        try {
            Tourist tourist = service.fetchTouristById(id);
            // method can return ResponseEntity Tourist obj
            return new ResponseEntity<Tourist>(tourist, HttpStatus.OK);
        } catch (Exception e) {
            // method can return ResponseEntity String obj
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-tourist")
    public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist) {
        try {
            String res = service.updateTouristInfo(tourist);
            return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // See here when names same no need to mention in PathVariable. Refer to
    // PathVariable Intellisense below. id = id, budget1 != budget
    @PatchMapping("/update-tourist/{id}/{budget1}")
    public ResponseEntity<String> updateTouristByIdWithBudget(@PathVariable("id") Integer id,
            @PathVariable("budget1") Double budget) {
        try {
            String res = service.updateTheTouristBudget(id, budget);
            return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/update-tourist/{id}")
    public ResponseEntity<String> deleteTouristById(@PathVariable("id") Integer id) {
        try {
            String res = service.deleteTouristInfoById(id);
            return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-tourists")
    public ResponseEntity<List<Tourist>> getAllTourists() {
        List<Tourist> touristList = service.fetchAllTouristInfo();
        return new ResponseEntity<List<Tourist>>(touristList, HttpStatus.OK);
    }

}
