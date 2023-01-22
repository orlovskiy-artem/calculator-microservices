package com.soalab.additionservice.controller;

import com.soalab.additionservice.service.AdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {
    @Autowired
    private AdditionService additionService;

    @GetMapping("/add/{a}/{b}")
    public ResponseEntity<Integer> add(@PathVariable String a, @PathVariable String b){
        try {
            Integer result = additionService.add(Integer.parseInt(a),Integer.parseInt(b));
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

