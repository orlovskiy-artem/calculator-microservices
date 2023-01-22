package com.soalab.multiplicationservice.controller.MultiplicationController;

import com.soalab.multiplicationservice.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {
    @Autowired
    private MultiplicationService multiplicationService;

    @GetMapping("/multiply/{a}/{b}")
    public ResponseEntity<Integer> multiply(@PathVariable String a, @PathVariable String b){
        try {
            Integer result = multiplicationService.multiply(Integer.parseInt(a),Integer.parseInt(b));
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
