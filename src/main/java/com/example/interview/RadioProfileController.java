package com.example.interview;

import com.example.interview.Interview.CreateRadioProfileRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RadioProfileController {

    public static final Logger logger = LoggerFactory.getLogger(RadioProfileController.class);

    @Autowired
    RadioProfileRepository radioProfileRepo;

    @RequestMapping(value = "/profiles/{id}")
    ResponseEntity<CreateRadioProfileRequest> getRadioProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(radioProfileRepo.findById(id));

    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRadioProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(radioProfileRepo.deleteProfile(id));

    }

    @RequestMapping(value = "/profiles/location/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> setRadioLocation(@PathVariable Integer id) {
        return ResponseEntity.ok(radioProfileRepo.setLocationById(id));

    }
}